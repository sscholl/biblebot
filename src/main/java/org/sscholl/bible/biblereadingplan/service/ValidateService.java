package org.sscholl.bible.biblereadingplan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblereadingplan.model.Passage;
import org.sscholl.bible.biblereadingplan.model.PlanDay;
import org.sscholl.bible.biblereadingplan.model.PlanInstance;
import org.sscholl.bible.biblereadingplan.model.PlanInstanceDay;
import org.sscholl.bible.biblereadingplan.repository.PlanDayRepository;
import org.sscholl.bible.biblereadingplan.repository.PlanInstanceDayRepository;
import org.sscholl.bible.biblereadingplan.repository.PlanInstanceRepository;
import org.sscholl.bible.common.model.dto.*;
import org.sscholl.bible.common.service.BibleCsvRepository;
import org.sscholl.bible.common.service.QueryParserService;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class ValidateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateService.class);

    @Autowired
    private PlanDayRepository planDayRepository;

    @Autowired
    private PlanInstanceRepository planInstanceRepository;

    @Autowired
    private PlanInstanceDayRepository planInstanceDayRepository;

    @Autowired
    private QueryParserService queryParserService;

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    /**
     * “At minute 55 past every hour.”
     */
    @Scheduled(cron = "${bibelreadingplan.validateservice.cron:0 55 */1 * * *}")
    @Transactional
    public void scheduleAll() {
        for (PlanInstance instance : planInstanceRepository.findAll()) {
            scheduleDays(instance);
        }
    }

    @Transactional
    public void scheduleDays(PlanInstance instance) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instance.getStartDate());
        for (PlanDay day : instance.getPlan().getDays()) {
            LOGGER.debug("Process PlanDay " + day);
            PlanInstanceDay instanceDay;
            Optional<PlanInstanceDay> result = instance
                    .getDays()
                    .stream()
                    .filter(i -> Objects.equals(i.getDay().getId(), day.getId()))
                    .findAny();
            if (result.isPresent()) {
                instanceDay = result.get();
            } else {
                LOGGER.debug("create new PlanInstanceDay for PlanDay " + day.getId());
                //create day
                instanceDay = new PlanInstanceDay();
                instanceDay.setPosted(false);
                instanceDay.setPlanInstance(instance);
                instanceDay.setDay(day);
                instance.getDays().add(instanceDay);
            }

            instanceDay.setScheduledDate(cal.getTime());
            // add one day for next instanceDay
            cal.add(Calendar.DATE, 1);
        }
        planInstanceRepository.save(instance);
    }

    @Transactional
    public void setDefaultValues(PlanDay day) {
        BibleDTO bibleDTO = bibleCsvRepository.findBible(day.getPlan().getBibleKey());
        for (Passage passage : day.getPassages()) {
            BookDTO bookDTO = null;
            ChapterDTO chapterDTO = null;
            if (passage.getBookNumber() != null || passage.getChapterNumber() != null) {
                bookDTO = bibleDTO.getBook(passage.getBookNumber());
                if (bookDTO != null) {
                    chapterDTO = bookDTO.getChapter(passage.getChapterNumber());
                }
            }

            // if no valid numbers found, try to find by title
            if (passage.getBookNumber() == null || passage.getChapterNumber() == null) {
                if (passage.getTitle() == null || "".equals(passage.getTitle())) {
                    throw new RuntimeException("No valid passage defined. ID: " + passage.getId());
                }
                List<PassageDTO> passageDTOS = queryParserService.process(passage.getTitle());
                Iterator<PassageDTO> it = passageDTOS.iterator();
                if (it.hasNext()) {
                    PassageDTO passageDTO = it.next();
                    passage.setBookNumber(passageDTO.getBookDTO().getNumber());
                    passage.setChapterNumber(passageDTO.getChapterDTO().getNumber());
                    passage.setVerseStart(passageDTO.getFirstVerse().getNumber());
                    passage.setVerseEnd(passageDTO.getLastVerse().getNumber());

                    // get new book and chapter
                    bookDTO = bibleDTO.getBook(passage.getBookNumber());
                    chapterDTO = bookDTO.getChapter(passage.getChapterNumber());
                }
            }

            if (bookDTO == null || chapterDTO == null) {
                throw new RuntimeException("No valid passage defined. Can not find book or chapter object. ID: " + passage.getId());
            }

            if (passage.getVerseStart() == null || passage.getVerseEnd() == null) {
                VerseDTO firstVerse = chapterDTO.getFirstVerse();
                VerseDTO lastVerse = chapterDTO.getLastVerse();
                if (passage.getVerseStart() == null && firstVerse != null) {
                    passage.setVerseStart(firstVerse.getNumber());
                }
                if (passage.getVerseEnd() == null && lastVerse != null) {
                    passage.setVerseEnd(lastVerse.getNumber());
                }
            }

            // if no title set, set a default one
            if (passage.getTitle() == null || "".equals(passage.getTitle())) {
                passage.setTitle(bookDTO.getGermanName() + " " + chapterDTO.getNumber() + ":"
                        + passage.getVerseStart() + "-" + passage.getVerseEnd());
            }
        }
        planDayRepository.save(day);
    }

    @Transactional
    public void setDefaultValues(PlanInstanceDay instanceDay) {
        if (instanceDay.getPosted() == null) {
            instanceDay.setPosted(false);
            planInstanceDayRepository.save(instanceDay);
        }

        if (instanceDay.getScheduledDate() == null) {
            scheduleDays(instanceDay.getPlanInstance());
        }
    }
}