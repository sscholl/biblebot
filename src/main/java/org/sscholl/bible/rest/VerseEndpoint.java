package org.sscholl.bible.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Chapter;
import org.sscholl.bible.model.Verse;
import org.sscholl.bible.service.BibleRepository;
import org.sscholl.bible.service.BookRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible/{bibleId}/book/{bookId}/chapter/{chapterId}/verse")
public class VerseEndpoint {

    @Autowired
    private BibleRepository bibleRepository;

    @Autowired
    private BookRepository bookRepository;

    @GET
    @Path("/{verseId}")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId, @PathParam("chapterId") String verseId) {
        StringBuilder response = new StringBuilder();

        Bible bible = bibleRepository.findBible(bibleId);
        Book book = bookRepository.findBook(bible, bookId);
        Chapter chapter = book.getChapter(Integer.parseInt(chapterId));
        Verse verse = chapter.getVerse(Integer.parseInt(chapterId));

        response.append(verse.toString());

        return response.toString();
    }

    @GET
    @Path("/{verseId}/text")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String getText(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId, @PathParam("chapterId") String verseId) {
        Bible bible = bibleRepository.findBible(bibleId);
        Book book = bible.getBook(Integer.parseInt(bookId));
        Chapter chapter = book.getChapter(Integer.parseInt(chapterId));
        Verse verse = chapter.getVerse(Integer.parseInt(chapterId));

        return verse.getText();
    }
}