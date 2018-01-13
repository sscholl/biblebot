package org.sscholl.bible.biblebot.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.Application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class SearchEndpointTest {

    @Autowired
    private SearchEndpoint searchEndpoint = new SearchEndpoint();

    @Test
    public void get() {
        //TODO
    }

    @Test
    public void keywords() {
        String keywords = searchEndpoint.keywords();
        System.out.println(keywords);
        assertThat(keywords.length(), allOf(greaterThan(1000), lessThan(20000)));
        assertThat(keywords, containsString(","));
        assertThat(keywords, containsString("b:"));
        assertThat(keywords, containsString("bible:"));
        assertThat(keywords, containsString("bibel:"));
        assertThat(keywords, containsString("1. mose"));
        assertThat(keywords, containsString("1kgs"));
        assertThat(keywords, containsString("1.könige"));
        assertThat(keywords, containsString("1. chronicles"));
        assertThat(keywords, containsString("2samuel"));
        assertThat(keywords, containsString("lam"));
        assertThat(keywords, containsString("lamentations,"));
        assertThat(keywords, containsString("thessalonians"));
        assertThat(keywords, containsString("1.timotheus,"));
        assertThat(keywords, containsString("jesaja"));
        assertThat(keywords, containsString("hebräer"));
        assertThat(keywords, containsString("2 petrus"));
        assertThat(keywords, containsString("2.petrus"));
        assertThat(keywords, containsString("Levitikus"));
        assertThat(keywords, containsString("1. Mose"));
        assertThat(keywords, containsString("1. Chronik"));
        assertThat(keywords, containsString("Hohelied"));
        assertThat(keywords, containsString("Psalm"));
        assertThat(keywords, containsString("Klagelieder"));
        assertThat(keywords, containsString("Philippians"));
        assertThat(keywords, containsString("Kolosser"));
        assertThat(keywords, containsString("3. Johannes"));
        assertThat(keywords, containsString("Offb"));
        assertThat(keywords, containsString("Offenbarung"));
    }
}