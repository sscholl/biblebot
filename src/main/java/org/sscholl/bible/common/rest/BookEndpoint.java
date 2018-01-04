package org.sscholl.bible.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.ChapterDTO;
import org.sscholl.bible.common.service.BibleCsvRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible/{bibleId}/book")
public class BookEndpoint {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId, @PathParam("bookId") String bookId) {
        StringBuilder response = new StringBuilder();

        BibleDTO bibleDTO = bibleCsvRepository.findBible(bibleId);
        BookDTO bookDTO = bibleCsvRepository.findBook(bibleDTO, bookId);

        response.append(bookDTO.toString()).append("<br/><br/>");
        for (ChapterDTO chapterDTO : bookDTO.getChapters()) {
            response.append(chapterDTO.toString()).append("<br/>");
        }

        return response.toString();
    }
}