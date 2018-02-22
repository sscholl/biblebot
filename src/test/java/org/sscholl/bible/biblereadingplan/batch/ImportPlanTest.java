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
    public void importPlanJob() throws Exception {

        Plan plan = new Plan();
        plan.setName("");
        plan.setBibleKey(bibleCsvRepository.getDefaultBible());
        plan.setDays(new LinkedList<>());
        planRepository.saveAndFlush(plan);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();


        Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        System.out.println(planRepository.findAll().size());
        System.out.println(planDayRepository.findAll().size());
        System.out.println(passageRepository.findAll().size());
    }
}