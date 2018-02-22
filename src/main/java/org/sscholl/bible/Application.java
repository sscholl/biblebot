package org.sscholl.bible;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.sscholl.bible.common", "org.sscholl.bible.rest", "org.sscholl.bible.biblebot", "org.sscholl.bible.biblereadingplan", "org.sscholl.bible.adapter",})
@EnableScheduling
@EnableBatchProcessing
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        System.out.println("***********************");
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//        System.out.println("***********************");
    }
}
