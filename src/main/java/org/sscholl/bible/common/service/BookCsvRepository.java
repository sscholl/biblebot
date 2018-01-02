package org.sscholl.bible.common.service;

import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.Bible;
import org.sscholl.bible.common.model.Book;

/**
 * Created by Simon on 01.10.2017.
 */
@Component
public class BookCsvRepository {

    public Book findBook(Bible bible, String idOrShortcut) {
        return bible.getBooks()
                .stream()
                .filter(b -> String.valueOf(b.getNumber()).equals(idOrShortcut) || b.getShortcuts().contains(idOrShortcut.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
