package org.sscholl.bible.biblereadingplan;

import org.sscholl.bible.biblereadingplan.model.*;

import java.util.Date;
import java.util.LinkedList;

public class TestData {

    static Plan plan;
    static PlanInstance planInstance;

    public static void createAll() {
        createPlan();
        createDays();
        createPlanInstance();
        createPlanInstanceDays();
    }

    public static void createPlanInstanceDays() {
        //set up instanceDay
        for (PlanDay day : plan.getDays()) {
            PlanInstanceDay instanceDay = new PlanInstanceDay();
            instanceDay.setPosted(false);
            instanceDay.setDay(day);
            instanceDay.setPlanInstance(planInstance);
            instanceDay.setScheduledDate(new Date());
            planInstance.getDays().add(instanceDay);
        }
    }

    public static void createPlanInstance() {
        // set up planInstance
        planInstance = new PlanInstance();
        planInstance.setChannel("#general");
        planInstance.setPlan(plan);
        planInstance.setDays(new LinkedList<>());
        planInstance.setStartDate(new Date());
    }

    public static PlanDay createDays() {
        // set up day
        Passage passage = new Passage();
        passage.setTitle("Mose 1:1-4");
        passage.setBibleKey("elb");
        passage.setBookNumber(1);
        passage.setChapterNumber(1);
        passage.setVerseStart(1);
        passage.setVerseEnd(4);

        PlanDay day = new PlanDay();
        day.setFree(false);
        day.setText("Heute lesen wir 1. Mose 1");

        day.setPassages(new LinkedList<>());
        day.getPassages().add(passage);

        day.setPlan(plan);
        plan.getDays().add(day);
        return day;
    }

    public static void createPlan() {
        // set up plan
        plan = new Plan();
        plan.setBibleKey("elb");
        plan.setName("Bibelleseplan 2018/2019");
        plan.setDays(new LinkedList<>());
    }

    public static Plan getPlan() {
        return plan;
    }

    public static void setPlan(Plan plan) {
        TestData.plan = plan;
    }

    public static PlanInstance getPlanInstance() {
        return planInstance;
    }

    public static void setPlanInstance(PlanInstance planInstance) {
        TestData.planInstance = planInstance;
    }
}
