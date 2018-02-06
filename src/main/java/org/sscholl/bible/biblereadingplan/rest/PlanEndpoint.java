package org.sscholl.bible.biblereadingplan.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblereadingplan.service.PostService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/biblereadingplan")
public class PlanEndpoint {

    @Autowired
    private PostService postService;

    @GET
    @Path("/execute")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String executePostService() {
        postService.process();

        return "OK";
    }

}