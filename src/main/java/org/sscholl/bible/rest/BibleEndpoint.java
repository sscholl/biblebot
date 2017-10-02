package org.sscholl.bible.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.service.BibleRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible")
public class BibleEndpoint {

    @Autowired
    private BibleRepository bibleRepository;

    @GET
    @Path("/{bibleId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId) {
        StringBuilder response = new StringBuilder();

        Bible bible = bibleRepository.findBible(bibleId);

        response.append(bible.toString()).append("<br/><br/>");
        for (Book book : bible.getBooks()) {
            response.append(book.toString()).append("<br/>");
        }

        return response.toString();
    }
}