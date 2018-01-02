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
@Path("/bible/{bibleId}/book/{bookId}/chapter")
public class ChapterEndpoint {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @Autowired
    private BookCsvRepository bookCsvRepository;

    @GET
    @Path("/{chapterId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId) {
        StringBuilder response = new StringBuilder();

        Bible bible = bibleCsvRepository.findBible(bibleId);
        Book book = bookCsvRepository.findBook(bible, bookId);
        Chapter chapter = book.getChapter(Integer.parseInt(chapterId));

        response.append(chapter.toString()).append("<br/><br/>");
        for (Verse verse : chapter.getVerses()) {
            response.append(verse.toString()).append("<br/>");
        }

        return response.toString();
    }
}