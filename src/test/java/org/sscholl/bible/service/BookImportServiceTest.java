package org.sscholl.bible.service;

import org.junit.Test;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;

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
            System.out.println(book);
        }
    }

}