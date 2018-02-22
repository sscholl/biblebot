package org.sscholl.bible.biblereadingplan;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"org.sscholl.bible.biblereadingplan", "org.sscholl.bible.common", "org.sscholl.bible.adapter",})
@EnableBatchProcessing
public class TestApplicationContext {

    @Bean
    JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

}
