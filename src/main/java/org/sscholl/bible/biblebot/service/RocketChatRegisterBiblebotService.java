package org.sscholl.bible.biblebot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.rocketchat.api.IntegrationCreateRequest;
import org.sscholl.rocketchat.api.IntegrationsResponse;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This component will register the integration to Rocket.Chat after the application context is loaded and the
 * Rocket.Chat API is reachable. It waits 300-600 seconds for the Rocket.Chat to come up.
 *
 * @author Simon Scholl
 */
@Component
public class RocketChatRegisterBiblebotService implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketChatRegisterBiblebotService.class);
    @Autowired
    RocketChatService rocketChatService;

    @Value("${biblebot.integration.active:false}")
    private boolean integrationActive = true;
    @Value("${biblebot.integration.host:localhost}")
    private String integrationHost = "localhost";
    @Value("${biblebot.integration.port:8081}")
    private int integrationPort = 8080;
    @Value("${biblebot.integration.name:biblebot}")
    private String integrationName = "biblebot";

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (integrationActive) {
            LOGGER.info("register integration at " + rocketChatService.getApiHost() + ":" + rocketChatService.getApiPort());

            if (rocketChatService.waitForApiHost() && rocketChatService.waitAndLoginToApi()) {
                IntegrationsResponse integrationsResponse = rocketChatService.listIntegrations();
                if (integrationsResponse.getIntegrations().stream()
                        .noneMatch(i -> i.getName() != null && i.getName().equals(integrationName))) {
                    IntegrationCreateRequest integrationCreateRequest = new IntegrationCreateRequest();
                    integrationCreateRequest.setType("webhook-outgoing");
                    integrationCreateRequest.setEvent("sendMessage");
                    integrationCreateRequest.setEnabled(true);
                    integrationCreateRequest.setName(integrationName);
                    integrationCreateRequest.setChannel("all_public_channels,all_private_groups,all_direct_messages");
                    integrationCreateRequest.setUsername("rocket.cat");
                    integrationCreateRequest.setToken(UUID.randomUUID().toString().replace("-", ""));
                    integrationCreateRequest.setEmoji(":book:");
                    integrationCreateRequest.setUrls(new ArrayList<>());
                    integrationCreateRequest.getUrls().add("http://" + integrationHost + ":" + integrationPort + "/chat");
                    integrationCreateRequest.setScriptEnabled(false);
                    rocketChatService.postMessage(integrationCreateRequest);
                } else {
                    LOGGER.info("integration " + integrationName + " already existing.");
                }
            }
        }
    }

}