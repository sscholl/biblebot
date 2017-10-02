package org.sscholl.slackbible.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Passage;
import org.sscholl.bible.service.BibleRepository;
import org.sscholl.slackbible.service.QueryParserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


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

    @POST
    @Path("/slack/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String slack(@FormParam("body.text") String query) {
        StringBuilder response = new StringBuilder();
        List<Passage> passages = queryParserService.process(query);
        if (passages.size() <= 1) {
            JSONObject json = new JSONObject();
            json.put("username", "Bible");
            json.put("text", "Bible passages by translation " + passages.get(0).getBible().getName());
            json.put("response_type", "in_channel");
            JSONArray attachments = new JSONArray();
            for (Passage passage : passages) {
                JSONObject jsonAttachment = new JSONObject();
                jsonAttachment.put("title", passage.getTitle());
                jsonAttachment.put("text", passage.getVerses().stream().map(verse -> "*" + verse.getId() + "* " + verse.getText()).reduce((s, s2) -> s + " " + s2).orElse(""));
                jsonAttachment.put("title_link", "https://www.bibleserver.com/text/NGU/" + passage.getQuery());
                //TODO: jsonAttachment.put("fallback", jsonAttachment.get("title") + ": " + jsonAttachment.get("text"));
                jsonAttachment.put("color", "good");
                jsonAttachment.put("footer", passage.getBible().getName());
                jsonAttachment.append("mrkdwn_in", "text");
                jsonAttachment.append("mrkdwn_in", "pretext");
                json.append("attachments", jsonAttachment);
            }
            response.append(json.toString());
        } else {
            response.append("{}");
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