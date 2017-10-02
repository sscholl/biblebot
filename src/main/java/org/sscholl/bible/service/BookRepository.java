package org.sscholl.bible.service;

import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;

/**
 * Created by simon on 01.10.2017.
 */
@Component
public class BookRepository {

    public Book findBook(Bible bible, String idOrShortcut) {
        return bible.getBooks()
                .stream()
                .filter(b -> String.valueOf(b.getId()).equals(idOrShortcut) || b.getShortcuts().contains(idOrShortcut.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
