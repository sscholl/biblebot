package org.sscholl.bible.biblereadingplan.service;

import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.bible.biblereadingplan.model.ReadingPlan;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanDay;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstance;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;
import org.sscholl.bible.common.model.Passage;
import org.sscholl.bible.common.service.BibleCsvRepository;
import org.sscholl.bible.common.service.BibleImportService;
import org.sscholl.bible.common.service.BookImportService;

import java.util.Date;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RocketChatPostServiceTest {

    private static RocketChatPostService service = new RocketChatPostService();

    public static void main(String[] args) {

        // given
        service.setBibleCsvRepository(new BibleCsvRepository());
        service.getBibleCsvRepository().setBibleImportService(new BibleImportService());
        service.getBibleCsvRepository().getBibleImportService().setBookImportService(new BookImportService());
        service.setRocketChatService(new RocketChatService());
        service.getRocketChatService().setUp();

        ReadingPlan plan = new ReadingPlan();
        ReadingPlanDay day = new ReadingPlanDay();
        ReadingPlanInstance planInstance = new ReadingPlanInstance();
        ReadingPlanInstanceDay instanceDay = new ReadingPlanInstanceDay();

        // set up plan
        plan.setBibleKey("elb");
        plan.setName("Bibelleseplan 2018/2019");
        plan.setDays(new LinkedList<>());

        // set up day
        Passage passage = new Passage();
        passage.setTitle("Mo 1:1-4");
        passage.setBibleKey("elb");
        passage.setBookNumber(1);
        passage.setChapterNumber(1);
        passage.setVerseStart(1);
        passage.setVerseEnd(4);

        day.setFree(false);
        day.setText("Heute lesen wir 1. Mose 1");

        day.setPassages(new LinkedList<>());
        day.getPassages().add(passage);

        day.setReadingPlan(plan);
        plan.getDays().add(day);

        // set up planInstance
        planInstance.setChannel("#general");
        planInstance.setReadingPlan(plan);
        planInstance.setDays(new LinkedList<>());
        planInstance.setStartTime(new Date());

        //set up instanceDay
        instanceDay.setPosted(false);
        instanceDay.setDay(day);
        instanceDay.setReadingPlanInstance(planInstance);
        planInstance.getDays().add(instanceDay);

        // when
        service.post(instanceDay);

        // then
        assertThat(instanceDay.getPosted(), is(true));
    }
}