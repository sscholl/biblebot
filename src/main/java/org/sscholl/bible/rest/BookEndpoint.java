package org.sscholl.bible.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Chapter;
import org.sscholl.bible.service.BibleRepository;
import org.sscholl.bible.service.BookRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible/{bibleId}/book")
public class BookEndpoint {

    @Autowired
    private BibleRepository bibleRepository;

    @Autowired
    private BookRepository bookRepository;

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId) {
        StringBuilder response = new StringBuilder();

        Bible bible = bibleRepository.findBible(bibleId);
        Book book = bookRepository.findBook(bible, bookId);

        response.append(book.toString()).append("<br/><br/>");
        for (Chapter chapter : book.getChapters()) {
            response.append(chapter.toString()).append("<br/>");
        }

        return response.toString();
    }
}