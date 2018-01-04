package org.sscholl.bible.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.service.BibleCsvRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/bible")
public class BibleEndpoint {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @GET
    @Path("/{bibleId}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("bibleId") String bibleId) {
        StringBuilder response = new StringBuilder();

        BibleDTO bibleDTO = bibleCsvRepository.findBible(bibleId);
        if (bibleDTO != null) {
            response.append(bibleDTO.toString()).append("<br/><br/>");
            for (BookDTO bookDTO : bibleDTO.getBooks()) {
                response.append(bookDTO.toString()).append("<br/>");
            }

            return response.toString();
        } else {
            return "no bibleDTO set";
        }
    }
}