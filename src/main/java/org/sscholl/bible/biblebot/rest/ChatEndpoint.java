package org.sscholl.bible.biblebot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblebot.service.QueryParserService;
import org.sscholl.bible.common.model.dto.PassageDTO;
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

    private final String botAlias = "Bible";

    @Autowired
    private QueryParserService queryParserService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public MessageSentResponse post(MessageSentRequest messageSentRequest) {
        LOGGER.debug("messageSentRequest" + messageSentRequest);

        if (botAlias.equals(messageSentRequest.getAlias())) {
            LOGGER.debug("Not processing this message, because was sent by myself.");
            return null;
        }

        List<PassageDTO> passageDTOS = queryParserService.process(messageSentRequest.getText());

        MessageSentResponse response = null;
        if (passageDTOS.size() >= 1) {
            LOGGER.debug("passageDTOS found: " + passageDTOS);

            response = new MessageSentResponse();
            response.setUsername(botAlias);
            //response.setText("Bible passage by translation " + passageDTOS.get(0).getBibleDTO().getName());
            response.setText("");
            response.setResponseType("in_channel");
            response.setAttachments(new ArrayList<>());
            for (PassageDTO passageDTO : passageDTOS) {
                Attachment attachment = new Attachment();
                attachment.setTitle(passageDTO.getTitle());
                attachment.setText(
                        passageDTO.getVerses().stream()
                                .map(verse -> "*" + verse.getNumber() + "* " + verse.getText())
                                .reduce((s, s2) -> s + " " + s2).orElse("")
                                + "\n\n"
                                + "_" + passageDTO.getBibleDTO().getName() + "_"
                );
                attachment.setTitleLink("https://www.bibleserver.com/text/NGU/" + passageDTO.getQuery());
                //TODO: attachment.setFallback(attachment.getTitle() + ": " + attachment.getText());
                attachment.setColor("good");
                attachment.setFooter(passageDTO.getBibleDTO().getName());
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