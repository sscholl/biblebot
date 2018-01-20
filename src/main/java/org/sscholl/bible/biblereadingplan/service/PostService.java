package org.sscholl.bible.biblereadingplan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblereadingplan.model.PlanInstanceDay;
import org.sscholl.bible.biblereadingplan.repository.PlanInstanceDayRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private RocketChatPostService rocketChatPostService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private PlanInstanceDayRepository planInstanceDayRepository;

    /**
     * Process all instance days that are not processed already, but are due
     *
     * “At minute 0 past every hour.”
     */
    @Scheduled(cron = "${biblereadingplan.postservice.cron:0 0 * * * *}")
    @Transactional
    public void process() {
        validateService.scheduleAll();
        for (PlanInstanceDay instanceDay : planInstanceDayRepository
                .findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc(new Date())) {
            validateService.setDefaultValues(instanceDay.getDay());
            validateService.setDefaultValues(instanceDay);
            if (rocketChatPostService.post(instanceDay)) {
                instanceDay.setPosted(true);
                planInstanceDayRepository.save(instanceDay);
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

    public PlanInstanceDayRepository getPlanInstanceDayRepository() {
        return planInstanceDayRepository;
    }

    public void setPlanInstanceDayRepository(PlanInstanceDayRepository planInstanceDayRepository) {
        this.planInstanceDayRepository = planInstanceDayRepository;
    }
}
