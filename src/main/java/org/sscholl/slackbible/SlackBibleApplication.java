package org.sscholl.slackbible;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.sscholl.slackbible", "org.sscholl.bible"})
public class SlackBibleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SlackBibleApplication.class, args);
    }
}