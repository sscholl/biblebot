package org.sscholl.bible.common.service;

import org.junit.Test;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Simon on 01.10.2017.
 */
public class BookImportServiceTest {

    private BookImportService bookImportService = new BookImportService();

    @Test
    public void loadBookConfig() {
        BibleDTO bibleDTO = new BibleDTO();

        bookImportService.loadBookConfig(bibleDTO);

        for (BookDTO bookDTO : bibleDTO.getBooks()) {
            assertThat("issue found for bookDTO " + bookDTO, bookDTO.getNumber(), notNullValue());
            assertThat("issue found for bookDTO " + bookDTO, bookDTO.getName(), notNullValue());
            assertThat("issue found for bookDTO " + bookDTO, bookDTO.getGermanName(), notNullValue());
            assertThat("issue found for bookDTO " + bookDTO, bookDTO.getTestament(), notNullValue());
            System.out.println(bookDTO);
        }
    }

}