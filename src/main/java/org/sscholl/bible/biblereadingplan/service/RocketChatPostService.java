package org.sscholl.bible.biblereadingplan.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanDay;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;
import org.sscholl.bible.common.model.Passage;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.ChapterDTO;
import org.sscholl.bible.common.model.dto.VerseDTO;
import org.sscholl.bible.common.service.BibleCsvRepository;
import org.sscholl.rocketchat.api.Attachment;
import org.sscholl.rocketchat.api.PostMessageRequest;
import org.sscholl.rocketchat.api.PostMessageResponse;

import java.util.LinkedList;
import java.util.List;

@Component
public class RocketChatPostService {

    private static Logger LOGGER = Logger.getLogger(RocketChatPostService.class);

    @Autowired
    private RocketChatService rocketChatService;

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    void post(ReadingPlanInstanceDay instanceDay) {
        ReadingPlanDay day = instanceDay.getDay();

        PostMessageRequest postMessageRequest = new PostMessageRequest();
        postMessageRequest.setChannel(instanceDay.getReadingPlanInstance().getChannel());
        postMessageRequest.setAlias(day.getReadingPlan().getName());
        postMessageRequest.setEmoji(":book:");
        postMessageRequest.setText(day.getText());
        postMessageRequest.setAttachments(new LinkedList<>());
        for (Passage passage : day.getPassages()) {
            BibleDTO bibleDTO = bibleCsvRepository.findBible(passage.getBibleKey());
            BookDTO bookDTO = bibleDTO.getBook(passage.getBookNumber());
            ChapterDTO chapterDTO = bookDTO.getChapter(passage.getChapterNumber());
            List<VerseDTO> verses = new LinkedList<>();
            for (int i = passage.getVerseStart(); i <= passage.getVerseEnd(); i++) {
                VerseDTO verseDTO = chapterDTO.getVerse(i);

                if (verseDTO != null) {
                    verses.add(chapterDTO.getVerse(i));
                } else {
                    LOGGER.error("VerseDTO " + i + " not found.");
                }
            }

            if (!verses.isEmpty()) {
                Attachment attachment = new Attachment();
                attachment.setTitle(passage.getTitle());
                attachment.setText(
                        verses.stream()
                                .map(verse -> "*" + verse.getNumber() + "* " + verse.getText())
                                .reduce((s, s2) -> s + " " + s2)
                                .orElse("")
                                + "\n\n"
                                + "_" + bibleDTO.getName() + "_"
                );
                attachment.setTitleLink("https://www.bibleserver.com/text/NGU/" + bookDTO.getGermanName() + chapterDTO.getNumber()
                        + ":" + passage.getVerseStart() + "-" + passage.getVerseEnd());
                attachment.setColor("good");
                postMessageRequest.getAttachments().add(attachment);
            }
        }

        LOGGER.info("post message to " + rocketChatService.getApiHost() + ":" + rocketChatService.getApiPort());
        LOGGER.debug("postMessageRequest " + postMessageRequest.toString());
        if (rocketChatService.waitForApiHost() && rocketChatService.waitAndLoginToApi()) {
            PostMessageResponse postMessageResponse = rocketChatService.postMessage(postMessageRequest);
            LOGGER.debug("postMessageResponse " + postMessageResponse.toString());

            if (postMessageResponse.getSuccess()) {
                instanceDay.setPosted(true);
            }
        } else {
            LOGGER.error("RocketChat not available");
        }
    }

    public RocketChatService getRocketChatService() {
        return rocketChatService;
    }

    public void setRocketChatService(RocketChatService rocketChatService) {
        this.rocketChatService = rocketChatService;
    }

    public BibleCsvRepository getBibleCsvRepository() {
        return bibleCsvRepository;
    }

    public void setBibleCsvRepository(BibleCsvRepository bibleCsvRepository) {
        this.bibleCsvRepository = bibleCsvRepository;
    }

}
