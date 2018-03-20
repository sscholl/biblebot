package org.sscholl.bible.common.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.Application;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Simon on 02.10.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class BibleCsvRepositoryIntegrationTest {

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    @Test
    public void getBibles() {
        assertThat("bibleRepository should load bibles automatically", bibleCsvRepository.getBibleDTOS().size(), is(7));
    }

    @Test
    public void getDefaultBible() {
        assertThat("bibleRepository should load bibles automatically", bibleCsvRepository.getDefaultBible(), equalTo("neue"));
    }

    @Test
    public void findBible() {
        assertThat("bibleRepository should load bibles automatically", bibleCsvRepository.findBible(bibleCsvRepository.getDefaultBible()).getName(), equalTo("Neue evangelistische Übersetzung"));
    }

    @Test
    public void checkContent() {
        assertThat("bibleRepository should load bibles automatically", bibleCsvRepository.findBible(bibleCsvRepository.getDefaultBible()).getBook(1).getChapter(1).getVerse(1).getText(), equalTo("Im Anfang schuf GottHimmel und Erde."));
    }

}