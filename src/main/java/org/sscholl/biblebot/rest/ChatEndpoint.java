package org.sscholl.biblebot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Passage;
import org.sscholl.biblebot.service.QueryParserService;
import org.sscholl.rocketchat.message.Attachment;
import org.sscholl.rocketchat.message.MessageSentRequest;
import org.sscholl.rocketchat.message.MessageSentResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    public MessageSentResponse post(MessageSentRequest messageSentRequest) {
        LOGGER.debug("messageSentRequest" + messageSentRequest);
        List<Passage> passages = queryParserService.process(messageSentRequest.getText());

        MessageSentResponse response = null;
        if (passages.size() >= 1) {
            LOGGER.debug("passages found: " + passages);

            response = new MessageSentResponse();
            response.setUsername("Bible");
            //response.setText("Bible passages by translation " + passages.get(0).getBible().getName());
            response.setText("");
            response.setResponseType("in_channel");
            response.setAttachments(new ArrayList<>());
            for (Passage passage : passages) {
                Attachment attachment = new Attachment();
                attachment.setTitle(passage.getTitle());
                attachment.setText(
                        passage.getVerses().stream().map(verse -> "*" + verse.getId() + "* " + verse.getText()).reduce((s, s2) -> s + " " + s2).orElse("")
                                + "\n\n"
                                + "_" + passage.getBible().getName() + "_"
                );
                attachment.setTitleLink("https://www.bibleserver.com/text/NGU/" + passage.getQuery());
                //TODO: attachment.setFallback(attachment.getTitle() + ": " + attachment.getText());
                attachment.setColor("good");
                attachment.setFooter(passage.getBible().getName());
                attachment.setMrkdwnIn(new ArrayList<>());
                attachment.getMrkdwnIn().add("text");
                attachment.getMrkdwnIn().add("pretext");
                response.getAttachments().add(attachment);
            }
        }

        LOGGER.debug("MessageSentResponse " + response);
        return response;
    }

}