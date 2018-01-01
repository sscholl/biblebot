package org.sscholl.bible.service;

import org.junit.Test;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by simon on 01.10.2017.
 */
public class BookImportServiceTest {

    private BookImportService bookImportService = new BookImportService();

    @Test
    public void loadBookConfig() {
        Bible bible = new Bible();

        bookImportService.loadBookConfig(bible);

        for (Book book : bible.getBooks()) {
            assertThat("issue found for book " + book, book.getId(), notNullValue());
            assertThat("issue found for book " + book, book.getName(), notNullValue());
            assertThat("issue found for book " + book, book.getGermanName(), notNullValue());
            assertThat("issue found for book " + book, book.getTestament(), notNullValue());
            System.out.println(book);
        }
    }

}