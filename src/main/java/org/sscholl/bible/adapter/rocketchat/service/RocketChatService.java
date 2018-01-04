package org.sscholl.bible.adapter.rocketchat.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

@Component
public class RocketChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketChatService.class);

    @Value("${biblebot.api.host:localhost}")
    private String apiHost = "localhost";
    @Value("${biblebot.api.port:8080}")
    private int apiPort = 8080;
    @Value("${biblebot.api.path:/api/v1}")
    private String apiBasePath = "/api/v1";
    @Value("${biblebot.api.path:admin}")
    private String apiUsername = "admin";
    @Value("${biblebot.api.path:/api/v1}")
    private String apiPassword = "/api/v1";

    private Client client;
    private WebTarget webTarget = null;
    private LoginResponse loginResponse;

    @PostConstruct
    public void setUp() {
        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 10000);
        configuration.property(ClientProperties.READ_TIMEOUT, 1000);
        client = ClientBuilder.newClient();
        try {
            webTarget = client.target(new URI("http", null, apiHost, apiPort, apiBasePath, null, null));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForApiHost() {
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

    public boolean waitAndLoginToApi() {
        LOGGER.info("wait 300 sec for REST API " + webTarget.getUri().toString() + " to come up");
        for (int i = 0; i < 300; i++) {
            try {
                loginResponse = getLoginResponse(apiUsername, apiPassword);
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
     * @param apiUsername username that is used to log in
     * @param apiPassword password
     * @return json object
     */
    public LoginResponse getLoginResponse(String apiUsername, String apiPassword) throws ProcessingException {
        WebTarget loginWebTarget = webTarget.path("login");
        Invocation.Builder invocationBuilder = loginWebTarget.request(MediaType.APPLICATION_JSON);
        LoginRequest login = new LoginRequest();
        login.setUsername(apiUsername);
        login.setPassword(apiPassword);

        Response response = invocationBuilder.post(Entity.json(login));
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatusInfo() != Response.Status.OK) {
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
     * @return json object
     */
    public IntegrationsResponse getIntegrationsResponse() {
        Invocation.Builder invocationBuilder = getInvocationBuilder(webTarget.path("integrations.list"));

        Response response = invocationBuilder.get();
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatusInfo() != Response.Status.OK) {
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
     * @param integrationCreateRequest json object
     * @return json object
     */
    public IntegrationCreateResponse getIntegrationCreateResponse(IntegrationCreateRequest integrationCreateRequest) {
        Invocation.Builder invocationBuilder = getInvocationBuilder(webTarget.path("integrations.create"));

        Response response = invocationBuilder.post(Entity.json(integrationCreateRequest));
        LOGGER.debug("Response      : " + response.toString());
        if (response.getStatusInfo() != Response.Status.OK) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        IntegrationCreateResponse integrationCreateResponse = response.readEntity(IntegrationCreateResponse.class);
        LOGGER.debug("Response Data : " + integrationCreateResponse);
        if (!integrationCreateResponse.getSuccess()) {
            throw new RuntimeException("Failed : can't create integration: " + integrationCreateResponse.getError());
        }
        return integrationCreateResponse;
    }

    public String getApiHost() {
        return apiHost;
    }

    public int getApiPort() {
        return apiPort;
    }

    public String getApiBasePath() {
        return apiBasePath;
    }

    public String getApiUsername() {
        return apiUsername;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    private Invocation.Builder getInvocationBuilder(WebTarget integrationCreateWebTarget) {
        Invocation.Builder invocationBuilder = integrationCreateWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("X-Auth-Token", loginResponse.getData().getAuthToken());
        invocationBuilder.header("X-User-Id", loginResponse.getData().getUserId());
        return invocationBuilder;
    }

}
