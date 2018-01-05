package org.sscholl.bible.biblebot.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sscholl.bible.Application;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.PassageDTO;
import org.sscholl.bible.common.service.QueryParserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Simon on 02.10.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class QueryParserServiceIntegrationTest {

    Logger LOG = Logger.getLogger(QueryParserServiceIntegrationTest.class);

    @Autowired
    private QueryParserService queryParserService;

    private String query = "asdj LUT asdsjlans dsdbyzantinefsdf lut12df ps 1 asjkldn aal psalm ps psalms lsd Elberfelder1905 lasd l 1. John 1,4 asdk nakknas  l 5 Mose 5:3-22. JALB SJdba kd fsf a.orn  #asd3 df #hjj7 5 alöks Johannes asssdfd 4:2 djöak sa sk ";

    @Test
    public void getRegexBible() {
        // when
        Pattern regex = queryParserService.getRegexBible();
        LOG.debug("Regex: " + regex);

        // then
        assertThat("Regex seems not valid: " + regex.toString(), regex.toString().length(), allOf(greaterThan(100), lessThan(200)));
    }

    @Test
    public void getRegexPassages() {
        // when
        Pattern regex = queryParserService.getRegexPassages();
        LOG.debug("Regex: " + regex);

        // then
        assertThat("Regex seems not valid: " + regex.toString(), regex.toString().length(), allOf(greaterThan(4000), lessThan(5000)));
    }

    @Test
    public void findBibleMatches() {
        // when
        Matcher matcher = queryParserService.findBibleMatches(query);

//        while (matcher.find()) {
//            LOGGER.debug("regex result: " + matcher.group() + " with " + matcher.groupCount() + " groups.");
//            for (int i = 1; i <= matcher.groupCount(); i++) {
//                LOGGER.debug("------------- " + matcher.group(i));
//            }
//        }

        // then
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(true));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.groupCount(), is(1));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(1), equalTo("lut"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(true));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.groupCount(), is(1));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(1), equalTo("elberfelder1905"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(false));
    }

    @Test
    public void findPassageMatches() {
        //when
        Matcher matcher = queryParserService.findPassagesMatches(query);

//        while (matcher.find()) {
//            LOGGER.debug("regex result: " + matcher.group() + " with " + matcher.groupCount() + " groups.");
//            for (int i = 1; i <= matcher.groupCount(); i++) {
//                LOGGER.debug("------------- " + matcher.group(i));
//            }
//        }

        // then
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(true));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.groupCount(), is(5));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(), equalTo("ps 1"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(1), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(2), equalTo("ps"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(3), equalTo("1"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(4), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(5), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(true));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.groupCount(), is(5));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(), equalTo("1. john 1,4"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(1), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(2), equalTo("1. john"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(3), equalTo("1"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(4), equalTo("4"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(5), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(true));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.groupCount(), is(5));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(), equalTo("5 mose 5:3-22"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(1), equalTo(null));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(2), equalTo("5 mose"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(3), equalTo("5"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(4), equalTo("3"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.group(5), equalTo("22"));
        assertThat("Regex result seems not valid: " + matcher.toString(), matcher.find(), is(false));
    }

    @Test
    public void findBible() {
        // when
        BibleDTO bibleDTO = queryParserService.findBible(query);

        // then
        assertThat("No bibleDTO found.", bibleDTO, notNullValue());
        assertThat("Wrong bibleDTO found.", bibleDTO.getName(), equalTo("Luther (1912)"));
    }

    @Test
    public void process() {
        //when
        List<PassageDTO> passageDTOS = queryParserService.process(query);

        // then
        assertThat("Passages result incorrect of " + passageDTOS.toString(), passageDTOS.size(), is(3));
        assertThat("Passages result incorrect of " + passageDTOS.get(0), passageDTOS.get(0).getBibleDTO().getName(), equalTo("Luther (1912)"));
        assertThat("Passages result incorrect of " + passageDTOS.get(0), passageDTOS.get(0).getBookDTO().getName(), equalTo("Psalms"));
        assertThat("Passages result incorrect of " + passageDTOS.get(0), passageDTOS.get(0).getChapterDTO().getNumber(), is(1));
        assertThat("Passages result incorrect of " + passageDTOS.get(0), passageDTOS.get(0).getVerses().size(), is(6));
        assertThat("Passages result incorrect of " + passageDTOS.get(1), passageDTOS.get(1).getBibleDTO().getName(), equalTo("Luther (1912)"));
        assertThat("Passages result incorrect of " + passageDTOS.get(1), passageDTOS.get(1).getBookDTO().getName(), equalTo("1 John"));
        assertThat("Passages result incorrect of " + passageDTOS.get(1), passageDTOS.get(1).getChapterDTO().getNumber(), is(1));
        assertThat("Passages result incorrect of " + passageDTOS.get(1), passageDTOS.get(1).getVerses().size(), is(1));
        assertThat("Passages result incorrect of " + passageDTOS.get(2), passageDTOS.get(2).getBibleDTO().getName(), equalTo("Luther (1912)"));
        assertThat("Passages result incorrect of " + passageDTOS.get(2), passageDTOS.get(2).getBookDTO().getName(), equalTo("Deuteronomy"));
        assertThat("Passages result incorrect of " + passageDTOS.get(2), passageDTOS.get(2).getChapterDTO().getNumber(), is(5));
        assertThat("Passages result incorrect of " + passageDTOS.get(2), passageDTOS.get(2).getVerses().size(), is(20));
    }

}