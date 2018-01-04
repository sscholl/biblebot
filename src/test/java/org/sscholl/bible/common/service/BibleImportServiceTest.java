package org.sscholl.bible.common.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.ChapterDTO;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Simon on 01.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BibleImportServiceTest {

    @Mock
    private BookImportService bookImportService;

    @InjectMocks
    private BibleImportService bibleImportService = new BibleImportService();

    @Test
    public void loadBibleConfig() {
        Set<BibleDTO> bibleDTOS = bibleImportService.loadBibleConfig();
        Assert.assertEquals(6, bibleDTOS.size());
        for (BibleDTO bibleDTO : bibleDTOS) {
            System.out.println(bibleDTO);
            int chaptersCount = 0;
            int versesCount = 0;
            for (BookDTO bookDTO : bibleDTO.getBooks()) {
                chaptersCount += bookDTO.getChapters().size();
                for (ChapterDTO chapterDTO : bookDTO.getChapters()) {
                    versesCount += chapterDTO.getVerses().size();
                }
            }
            System.out.println("chaptersCount: " + chaptersCount + ", versesCount:" + versesCount);
            assertThat("a bibleDTO needs to have books", bibleDTO.getBooks().size(), greaterThan(0));
            assertThat("a bibleDTO needs to have chapters", chaptersCount, greaterThan(0));
            assertThat("a bibleDTO needs to have verses", versesCount, greaterThan(0));
        }
        verify(bookImportService, times(bibleDTOS.size())).loadBookConfig(any());
    }
}