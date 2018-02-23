package org.sscholl.bible.biblereadingplan.batch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.biblereadingplan.TestApplicationContext;
import org.sscholl.bible.biblereadingplan.model.Plan;
import org.sscholl.bible.biblereadingplan.model.PlanDay;
import org.sscholl.bible.biblereadingplan.repository.PassageRepository;
import org.sscholl.bible.biblereadingplan.repository.PlanDayRepository;
import org.sscholl.bible.biblereadingplan.repository.PlanRepository;
import org.sscholl.bible.common.service.BibleCsvRepository;

import java.util.LinkedList;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestApplicationContext.class})
public class ImportPlanTest {

    @Autowired
    Step importPlanDaysStep;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanDayRepository planDayRepository;
    @Autowired
    private PassageRepository passageRepository;
    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @Test
    public void importPlanJob2() {
        planRepository.deleteAll();


        PlanDay planDay = new PlanDay();
        planDay.setText("delete me");
        Plan plan = new Plan();
        plan.setName("");
        plan.setBibleKey(bibleCsvRepository.getDefaultBible());
        plan.setDays(new LinkedList<>());
        plan.getDays().add(planDay);
        planDay.setPlan(plan);
        planRepository.saveAndFlush(plan);
        planDayRepository.saveAndFlush(planDay);


        Plan plan2 = planRepository.findAll().get(0);
        PlanDay planDay2;
        int index = 0;
        if (plan2.getDays().size() <= index) {
            org.springframework.util.Assert.isTrue(plan2.getDays().size() == index, "dayNumber must increment value");
            planDay2 = new PlanDay();
            planDay2.setPlan(plan2);
            plan2.getDays().add(index, planDay2);
        } else {
            planDay2 = plan2.getDays().get(index);
        }
        planDay2.setText("");
        planDayRepository.saveAndFlush(planDay2);
        planRepository.saveAndFlush(plan2);


        Plan plan3 = planRepository.findAll().get(0);
        PlanDay planDay3 = plan3.getDays().get(0);
        Assert.assertEquals("", planDay3.getText());

        Assert.assertEquals(1, planRepository.findAll().size());
        Assert.assertEquals(1, planDayRepository.findAll().size());
    }

    @Test
    public void importPlanJob() throws Exception {
        planRepository.deleteAll();


        PlanDay planDay = new PlanDay();
        planDay.setText("delete me");
        Plan plan = new Plan();
        plan.setName("");
        plan.setBibleKey(bibleCsvRepository.getDefaultBible());
        plan.setDays(new LinkedList<>());
        plan.getDays().add(planDay);
        planDay.setPlan(plan);
        planRepository.saveAndFlush(plan);
        planDayRepository.saveAndFlush(planDay);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();


        Plan plan3 = planRepository.findAll().get(0);
        PlanDay planDay3 = plan3.getDays().get(0);
        Assert.assertEquals("", planDay3.getText());
        Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        Assert.assertEquals(1, planRepository.findAll().size());
        Assert.assertEquals(730, planDayRepository.findAll().size());
        Assert.assertEquals(1171, passageRepository.findAll().size());
    }
}