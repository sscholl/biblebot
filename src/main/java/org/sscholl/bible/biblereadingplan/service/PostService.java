package org.sscholl.bible.biblereadingplan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;
import org.sscholl.bible.biblereadingplan.repository.ReadingPlanInstanceDayRepository;

import java.util.Date;

@Component
public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private RocketChatPostService rocketChatPostService;

    @Autowired
    private ReadingPlanInstanceDayRepository readingPlanInstanceDayRepository;

    /**
     * Process all instance days that are not processed already, but are due
     */
    //@Scheduled(cron = "0 /5 * * * ?")
    @Scheduled(cron = "*/10 * * * * *")
    public void process() {
        for (ReadingPlanInstanceDay day : readingPlanInstanceDayRepository
                .findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc(new Date())) {
            if (rocketChatPostService.post(day)) {
                day.setPosted(true);
                readingPlanInstanceDayRepository.save(day);
            } else {
                LOGGER.error("Could not post message.");
            }
        }
    }

    public RocketChatPostService getRocketChatPostService() {
        return rocketChatPostService;
    }

    public void setRocketChatPostService(RocketChatPostService rocketChatPostService) {
        this.rocketChatPostService = rocketChatPostService;
    }

    public ReadingPlanInstanceDayRepository getReadingPlanInstanceDayRepository() {
        return readingPlanInstanceDayRepository;
    }

    public void setReadingPlanInstanceDayRepository(ReadingPlanInstanceDayRepository readingPlanInstanceDayRepository) {
        this.readingPlanInstanceDayRepository = readingPlanInstanceDayRepository;
    }
}
