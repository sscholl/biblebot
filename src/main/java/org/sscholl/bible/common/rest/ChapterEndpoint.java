package org.sscholl.bible.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.ChapterDTO;
import org.sscholl.bible.common.model.dto.VerseDTO;
import org.sscholl.bible.common.service.BibleCsvRepository;

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

    @GET
    @Path("/{chapterId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId, @PathParam("chapterId") String chapterId) {
        StringBuilder response = new StringBuilder();

        BibleDTO bibleDTO = bibleCsvRepository.findBible(bibleId);
        BookDTO bookDTO = bibleCsvRepository.findBook(bibleDTO, bookId);
        ChapterDTO chapterDTO = bookDTO.getChapter(Integer.parseInt(chapterId));

        response.append(chapterDTO.toString()).append("<br/><br/>");
        for (VerseDTO verseDTO : chapterDTO.getVerses()) {
            response.append(verseDTO.toString()).append("<br/>");
        }

        return response.toString();
    }
}