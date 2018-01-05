package org.sscholl.bible.biblereadingplan.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.biblereadingplan.TestApplicationContext;
import org.sscholl.bible.biblereadingplan.TestData;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanDay;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;

import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestApplicationContext.class})
public class PlanInstanceDayRepositoryTest {

    @Autowired
    private ReadingPlanRepository planRepository;

    @Autowired
    private ReadingPlanDayRepository planDayRepository;

    @Autowired
    private ReadingPlanInstanceRepository planInstanceRepository;

    @Autowired
    private ReadingPlanInstanceDayRepository planInstanceDayRepository;

    @Before
    public void setUp() {
        // given
        TestData.createAll();

        // when
        planRepository.saveAndFlush(TestData.getPlan());
        for (ReadingPlanDay day : TestData.getPlan().getDays()) {
            planDayRepository.saveAndFlush(day);
        }
        planInstanceRepository.saveAndFlush(TestData.getPlanInstance());
        for (ReadingPlanInstanceDay instanceDay : TestData.getPlanInstance().getDays()) {
            planInstanceDayRepository.saveAndFlush(instanceDay);
        }
    }

    @Test
    public void findAllByIsPostedIsFalse() {
        //then
        for (ReadingPlanInstanceDay instanceDay : planInstanceDayRepository.findAllByIsPostedIsFalse()) {
            System.out.println(instanceDay);
        }
    }

    @Test
    public void findAllByIsPostedIsFalseAndScheduledDateBefore() {

        //then
        for (ReadingPlanInstanceDay instanceDay : planInstanceDayRepository.
                findAllByIsPostedIsFalseAndScheduledDateBefore(new Date())) {
            System.out.println(instanceDay);
        }
    }

    @Test
    public void findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc() {
        //then
        for (ReadingPlanInstanceDay instanceDay : planInstanceDayRepository
                .findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc(new Date())) {
            System.out.println(instanceDay);
        }
    }
}