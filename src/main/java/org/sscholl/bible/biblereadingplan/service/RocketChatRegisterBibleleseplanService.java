package org.sscholl.bible.biblereadingplan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;
import org.sscholl.rocketchat.api.IntegrationCreateRequest;
import org.sscholl.rocketchat.api.IntegrationsResponse;

/**
 * This component will register the integration to Rocket.Chat after the application context is loaded and the
 * Rocket.Chat API is reachable. It waits 300-600 seconds for the Rocket.Chat to come up.
 *
 * @author Simon Scholl
 */
// deactivated because incomming webhooks are not supported yet
//@Component
public class RocketChatRegisterBibleleseplanService implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketChatRegisterBibleleseplanService.class);
    @Autowired
    RocketChatService rocketChatService;
    @Value("${biblebot.integration.host:localhost}")
    private String integrationHost = "localhost";
    @Value("${biblebot.integration.port:8081}")
    private int integrationPort = 8080;
    @Value("${biblereadingplan.integration.name:biblereadingplan}")
    private String integrationName = "biblereadingplan";

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.info("register integration at " + rocketChatService.getApiHost() + ":" + rocketChatService.getApiPort());

        if (rocketChatService.waitForApiHost() && rocketChatService.waitAndLoginToApi()) {
            IntegrationsResponse integrationsResponse = rocketChatService.listIntegrations();
            if (integrationsResponse.getIntegrations().stream()
                    .noneMatch(i -> i.getName() != null && i.getName().equals(integrationName))) {
                IntegrationCreateRequest integrationCreateRequest = new IntegrationCreateRequest();
                integrationCreateRequest.setType("webhook-incoming");
                integrationCreateRequest.setEnabled(true);
                integrationCreateRequest.setName(integrationName);
                integrationCreateRequest.setUsername("rocket.cat");
                integrationCreateRequest.setEmoji(":book:");
                integrationCreateRequest.setScriptEnabled(false);
                rocketChatService.postMessage(integrationCreateRequest);
            } else {
                LOGGER.info("integration " + integrationName + " already existing.");
            }
        }
    }

}