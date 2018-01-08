package org.sscholl.bible.biblereadingplan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.bible.biblereadingplan.model.Passage;
import org.sscholl.bible.biblereadingplan.model.PlanDay;
import org.sscholl.bible.biblereadingplan.model.PlanInstanceDay;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketChatPostService.class);

    @Autowired
    private RocketChatService rocketChatService;

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    public boolean post(PlanInstanceDay instanceDay) {
        PlanDay day = instanceDay.getDay();

        if (day.isFree() && (day.getText() == null || "".equals(day.getText()))) {
            return true;
        }

        PostMessageRequest postMessageRequest = new PostMessageRequest();
        postMessageRequest.setChannel(instanceDay.getPlanInstance().getChannel());
        postMessageRequest.setAlias(day.getPlan().getName());
        postMessageRequest.setEmoji(":book:");
        postMessageRequest.setText(day.getText());
        postMessageRequest.setAttachments(new LinkedList<>());
        for (Passage passage : day.getPassages()) {
            BibleDTO bibleDTO = bibleCsvRepository.findBible(day.getPlan().getBibleKey());
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
                if (postMessageRequest.getAttachments().size() > 0) {
                    attachment.setCollapsed(true);
                }
                postMessageRequest.getAttachments().add(attachment);
            }
        }

        LOGGER.info("post message to " + rocketChatService.getApiHost() + ":" + rocketChatService.getApiPort());
        LOGGER.debug("postMessageRequest " + postMessageRequest.toString());
        if (rocketChatService.waitForApiHost() && rocketChatService.waitAndLoginToApi()) {
            PostMessageResponse postMessageResponse = rocketChatService.postMessage(postMessageRequest);
            LOGGER.debug("postMessageResponse " + postMessageResponse.toString());

            if (postMessageResponse.getSuccess()) {
                return true;
            } else {
                LOGGER.error("Could not post message: " + postMessageResponse.getMessage());
            }
        } else {
            LOGGER.error("RocketChat not available");
        }
        return false;
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
