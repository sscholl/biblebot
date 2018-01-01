package org.sscholl.biblebot.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Passage;
import org.sscholl.biblebot.service.QueryParserService;
import org.sscholl.rocketchat.message.MessageSent;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Component
@Path("/chat")
public class ChatEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatEndpoint.class);

    @Autowired
    private QueryParserService queryParserService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String post(MessageSent messageSent) {
        LOGGER.debug("messageSent", messageSent);
        StringBuilder response = new StringBuilder();
        List<Passage> passages = queryParserService.process(messageSent.getText());
        if (passages.size() >= 1) {
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

}