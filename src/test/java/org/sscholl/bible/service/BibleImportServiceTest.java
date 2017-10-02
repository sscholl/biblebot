package org.sscholl.bible.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Chapter;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by simon on 01.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BibleImportServiceTest {

    @Mock
    private BookImportService bookImportService;

    @InjectMocks
    private BibleImportService bibleImportService = new BibleImportService();

    @Test
    public void loadBibleConfig() {
        Set<Bible> bibles = bibleImportService.loadBibleConfig();
        Assert.assertEquals(6, bibles.size());
        for (Bible bible : bibles) {
            System.out.println(bible);
            int chaptersCount = 0;
            int versesCount = 0;
            for (Book book : bible.getBooks()) {
                chaptersCount += book.getChapters().size();
                for (Chapter chapter : book.getChapters()) {
                    versesCount += chapter.getVerses().size();
                }
            }
            System.out.println("chaptersCount: " + chaptersCount + ", versesCount:" + versesCount);
            assertThat("a bible needs to have books", bible.getBooks().size(), greaterThan(0));
            assertThat("a bible needs to have chapters", chaptersCount, greaterThan(0));
            assertThat("a bible needs to have verses", versesCount, greaterThan(0));
        }
        verify(bookImportService, times(bibles.size())).loadBookConfig(any());
    }
}