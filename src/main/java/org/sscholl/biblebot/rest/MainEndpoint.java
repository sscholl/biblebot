package org.sscholl.biblebot.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/")
public class MainEndpoint {

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String get() {
        return "Hello";
    }

}