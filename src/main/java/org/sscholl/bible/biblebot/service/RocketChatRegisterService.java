package org.sscholl.bible.biblebot.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.sscholl.rocketchat.api.*;

import javax.annotation.PostConstruct;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This component will register the integration to Rocket.Chat after the application context is loaded and the
 * Rocket.Chat API is reachable. It waits 300-600 seconds for the Rocket.Chat to come up.
 *
 * @author Simon Scholl
 */
@Component
public class RocketChatRegisterService implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketChatRegisterService.class);

    @Value("${biblebot.integration.host:localhost}")
    private String integrationHost = "localhost";
    @Value("${biblebot.integration.port:8081}")
    private int integrationPort = 8080;
    @Value("${biblebot.integration.name:biblebot}")
    private String integrationName = "biblebot";
    @Value("${biblebot.api.host:localhost}")
    private String apiHost = "localhost";
    @Value("${biblebot.api.port:8080}")
    private int apiPort = 8080;
    @Value("${biblebot.api.path:/api/v1}")
    private String apiBasePath = "/api/v1";

    private Client client;
    private WebTarget webTarget = null;
    private LoginResponse loginResponse;

    @PostConstruct
    public void setUp() {
        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 300000);
        configuration.property(ClientProperties.READ_TIMEOUT, 1000);
        client = ClientBuilder.newClient();
        try {
            webTarget = client.target(new URI("http", null, apiHost, apiPort, apiBasePath, null, null));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.info("register integration at " + apiHost + ":" + apiPort);

        if (waitForApiHost() && waitAndLoginToApi()) {
            IntegrationsResponse integrationsResponse = getIntegrationsResponse(loginResponse);
            if (integrationsResponse.getIntegrations().stream()
                    .noneMatch(i -> i.getName() != null && i.getName().equals(integrationName))) {
                getIntegrationCreateResponse(loginResponse);
            } else {
                LOGGER.info("integration " + integrationName + " already existing.");
            }
        }
    }

    private boolean waitForApiHost() {
        LOGGER.info("wait 60 sec for " + apiHost + ":" + apiPort + " to come up");
        for (int i = 0; i < 60; i++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(apiHost, apiPort), 1000);
                LOGGER.info("connected to " + apiHost + ":" + apiPort);
                return true;
            } catch (IOException e) {
                // ok, try another time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        LOGGER.error("couldn't connect to apiHost.");
        return false;
    }

    private boolean waitAndLoginToApi() {
        LOGGER.info("wait 300 sec for REST API " + webTarget.getUri().toString() + " to come up");
        for (int i = 0; i < 300; i++) {
            try {
                loginResponse = getLoginResponse();
                LOGGER.info("logged in.");
                return true;
            } catch (ProcessingException e) {
                // ok, try another time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        LOGGER.error("couldn't connect to api.");
        return false;
    }

    /**
     * curl http://localhost:8080/api/v1/login -d "username=admin&password=Mypa55"
     * {
     * "status": "success",
     * "data": {
     * "authToken": "m7d8tRN-L1oOdj9L-m_poiEIjbYMSYb5fDALn1nX0Pr",
     * "userId": "yudMoQc6dvNx5PmTb"
     * }
     * }
     *
     * @return json object
     */
    private LoginResponse getLoginResponse() throws ProcessingException {
        WebTarget loginWebTarget = webTarget.path("login");
        Invocation.Builder invocationBuilder = loginWebTarget.request(MediaType.APPLICATION_JSON);
        LoginRequest login = new LoginRequest();
        login.setUsername("admin");
        login.setPassword("Mypa55");
        Response response = invocationBuilder.post(Entity.json(login));
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
        }
        LoginResponse loginResponse = response.readEntity(LoginResponse.class);
        LOGGER.debug("Response Data : " + loginResponse);
        if (!loginResponse.getStatus().equals("success")) {
            throw new RuntimeException("Failed : Login response status: " + loginResponse.getStatus());
        }
        return loginResponse;
    }

    /**
     * curl -H "X-Auth-Token: m7d8tRN-L1oOdj9L-m_poiEIjbYMSYb5fDALn1nX0Pr" \
     * -H "X-User-Id: yudMoQc6dvNx5PmTb" \
     * -H "Content-type: application/json" \
     * http://localhost:8080/api/v1/integrations.list
     *
     * @param loginResponse json object
     * @return json object
     */
    private IntegrationsResponse getIntegrationsResponse(LoginResponse loginResponse) {
        WebTarget integrationsWebTarget = webTarget.path("integrations.list");
        Invocation.Builder invocationBuilder = integrationsWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("X-Auth-Token", loginResponse.getData().getAuthToken());
        invocationBuilder.header("X-User-Id", loginResponse.getData().getUserId());
        Response response = invocationBuilder.get();
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        IntegrationsResponse integrationsResponse = response.readEntity(IntegrationsResponse.class);
        LOGGER.debug("Response Data : " + integrationsResponse);
        return integrationsResponse;
    }

    /**
     * curl -H "X-Auth-Token: m7d8tRN-L1oOdj9L-m_poiEIjbYMSYb5fDALn1nX0Pr" \
     * -H "X-User-Id: yudMoQc6dvNx5PmTb" \
     * -H "Content-type: application/json" \
     * http://localhost:8080/api/v1/integrations.create \
     * -d '{ "type": "webhook-outgoing", "event": "sendMessage", "enabled": true, "name": "biblebot", "channel": "all_public_channels,all_private_groups,all_direct_messages", "username": "rocket.cat", "urls": ["http://biblebot:8081/chat"], "scriptEnabled": false }'
     *
     * @param loginResponse json object
     * @return json object
     */
    private IntegrationCreateResponse getIntegrationCreateResponse(LoginResponse loginResponse) {
        WebTarget integrationCreateWebTarget = webTarget.path("integrations.create");
        Invocation.Builder invocationBuilder = integrationCreateWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("X-Auth-Token", loginResponse.getData().getAuthToken());
        invocationBuilder.header("X-User-Id", loginResponse.getData().getUserId());

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
        Response response = invocationBuilder.post(Entity.json(integrationCreateRequest));
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        IntegrationCreateResponse integrationCreateResponse = response.readEntity(IntegrationCreateResponse.class);
        LOGGER.debug("Response Data : " + integrationCreateResponse);
        if (!integrationCreateResponse.getSuccess()) {
            throw new RuntimeException("Failed : can't create integration: " + integrationCreateResponse.getError());
        }
        return integrationCreateResponse;
    }

}