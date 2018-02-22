package org.sscholl.bible.biblereadingplan.rest;

import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.biblereadingplan.service.PostService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Component
@Path("/biblereadingplan")
public class PlanEndpoint {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    private PostService postService;
    @Autowired
    private Job importPlanJob;
    @Autowired
    private JobExplorer jobExplorer;

    @GET
    @Path("/execute")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String executePostService() {
        postService.process();

        return "OK";
    }

    @GET
    @Path("/import")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String executeImportPlanJob() throws Exception {

        List<JobInstance> instances = jobExplorer.getJobInstances("importPlanJob", 0, 1);
        JobInstance lastInstance = null;
        if (!instances.isEmpty()) {
            lastInstance = instances.get(0);
            List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(lastInstance);
            if (!instances.isEmpty()) {
                JobExecution je = jobExecutions.get(0);
            }
        }

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importPlanJob, jobParameters);

        return "OK";
    }

}