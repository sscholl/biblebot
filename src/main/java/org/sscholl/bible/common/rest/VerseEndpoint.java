package org.sscholl.bible.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.Bible;
import org.sscholl.bible.common.model.Book;
import org.sscholl.bible.common.model.Chapter;
import org.sscholl.bible.common.model.Verse;
import org.sscholl.bible.common.service.BibleCsvRepository;
import org.sscholl.bible.common.service.BookCsvRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible/{bibleId}/book/{bookId}/chapter/{chapterId}/verse")
public class VerseEndpoint {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @Autowired
    private BookCsvRepository bookCsvRepository;

    @GET
    @Path("/{verseId}")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId, @PathParam("chapterId") String verseId) {
        StringBuilder response = new StringBuilder();

        Bible bible = bibleCsvRepository.findBible(bibleId);
        Book book = bookCsvRepository.findBook(bible, bookId);
        Chapter chapter = book.getChapter(Integer.parseInt(chapterId));
        Verse verse = chapter.getVerse(Integer.parseInt(chapterId));

        response.append(verse.toString());

        return response.toString();
    }

    @GET
    @Path("/{verseId}/text")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String getText(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId, @PathParam("chapterId") String verseId) {
        Bible bible = bibleCsvRepository.findBible(bibleId);
        Book book = bible.getBook(Integer.parseInt(bookId));
        Chapter chapter = book.getChapter(Integer.parseInt(chapterId));
        Verse verse = chapter.getVerse(Integer.parseInt(chapterId));

        return verse.getText();
    }
}