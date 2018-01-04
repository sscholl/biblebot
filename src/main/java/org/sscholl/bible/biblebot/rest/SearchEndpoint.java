package org.sscholl.bible.biblebot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblebot.service.QueryParserService;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.PassageDTO;
import org.sscholl.bible.common.service.BibleCsvRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/search")
public class SearchEndpoint {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @Autowired
    private QueryParserService queryParserService;

    @GET
    @Path("/{query}")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String get(@PathParam("query") String query) {
        StringBuilder response = new StringBuilder();
        for (PassageDTO passageDTO : queryParserService.process(query)) {
            response.append(passageDTO.toString()).append("<br/><br/>");
        }
        return response.toString();
    }

    @GET
    @Path("/keywords")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String keywords() {
        StringBuilder response = new StringBuilder();
        response.append("b:,bible:,");
        for (BookDTO bookDTO : bibleCsvRepository.findBible(bibleCsvRepository.getDefaultBible()).getBooks()) {
            for (String shortcut : bookDTO.getShortcuts()) {
                response.append(shortcut).append(",");
            }
        }
        return response.toString();
    }

}