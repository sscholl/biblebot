package org.sscholl.bible.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Passage;
import org.sscholl.bible.service.BibleRepository;
import org.sscholl.biblebot.service.QueryParserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/search")
public class SearchEndpoint {

    @Autowired
    private BibleRepository bibleRepository;

    @Autowired
    private QueryParserService queryParserService;

    @GET
    @Path("/{query}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("query") String query) {
        StringBuilder response = new StringBuilder();
        for (Passage passage : queryParserService.process(query)) {
            response.append(passage.toString()).append("<br/><br/>");
        }
        return response.toString();
    }

    @GET
    @Path("/keywords")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String keywords() {
        StringBuilder response = new StringBuilder();
        response.append("b:,bible:,");
        for (Book book : bibleRepository.findBible(bibleRepository.getDefaultBible()).getBooks()) {
            for (String shortcut : book.getShortcuts()) {
                response.append(shortcut).append(",");
            }
        }
        return response.toString();
    }

}