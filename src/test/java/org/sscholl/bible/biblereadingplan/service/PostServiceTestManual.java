package org.sscholl.bible.biblereadingplan.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.Application;
import org.sscholl.bible.biblereadingplan.TestData;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PostServiceTestManual {

    @Autowired
    private static PostService service = new PostService();

    public static void main(String[] args) {

        // given
        TestData.createAll();

        // when
        service.process();

        // then

    }
}