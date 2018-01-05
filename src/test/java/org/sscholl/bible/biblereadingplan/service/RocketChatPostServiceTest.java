package org.sscholl.bible.biblereadingplan.service;

import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.bible.biblereadingplan.TestData;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;
import org.sscholl.bible.common.service.BibleCsvRepository;
import org.sscholl.bible.common.service.BibleImportService;
import org.sscholl.bible.common.service.BookImportService;

public class RocketChatPostServiceTest {

    private static RocketChatPostService service = new RocketChatPostService();

    public static void setUp() {
        service.setBibleCsvRepository(new BibleCsvRepository());
        service.getBibleCsvRepository().setBibleImportService(new BibleImportService());
        service.getBibleCsvRepository().getBibleImportService().setBookImportService(new BookImportService());
        service.setRocketChatService(new RocketChatService());
        service.getRocketChatService().setUp();
    }

    public static void main(String[] args) {
        setUp();

        // given
        TestData.createAll();

        for (ReadingPlanInstanceDay instanceDay : TestData.getPlanInstance().getDays()) {
            // when
            service.post(instanceDay);

            // then
//            assertThat(instanceDay.getPosted(), is(true));
        }
    }
}