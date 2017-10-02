package org.sscholl.slackbible.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.*;
import org.sscholl.bible.service.BibleRepository;
import org.sscholl.bible.service.BookRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simon on 02.10.2017.
 */
@Component
public class QueryParserService {

    Logger LOG = Logger.getLogger(QueryParserService.class);

    @Autowired
    private BibleRepository bibleRepository;

    @Autowired
    private BookRepository bookRepository;

    private Pattern regexBibles;

    private Pattern regexPassages;

    public Pattern getRegexBible() {
        if (regexBibles == null) {
            Set<String> shortcutsBible = bibleRepository.getBibles()
                    .stream()
                    .map(Bible::getShortcuts)
                    .reduce((strings, strings2) -> Stream.concat(strings.stream(), strings2.stream()).collect(Collectors.toSet()))
                    .orElse(new HashSet<>());

            regexBibles = Pattern.compile(" (" + shortcutsBible.stream().reduce((s, s2) -> s + "|" + s2).orElse("") + ") ");
        }
        return regexBibles;
    }

    public Pattern getRegexPassages() {
        if (regexPassages == null) {
            Set<String> shortcutsBooks = bibleRepository.findBible(bibleRepository.getDefaultBible())
                    .getBooks()
                    .stream()
                    .map(Book::getShortcuts)
                    .reduce((strings, strings2) -> Stream.concat(strings.stream(), strings2.stream()).collect(Collectors.toSet()))
                    .orElse(new HashSet<>());

            regexPassages = Pattern.compile("([a-zA-Z0-9])?("
                    + shortcutsBooks.stream().reduce((s, s2) -> s + "|" + s2).orElse("")
                    + ")\\.?\\s*(\\d{1,3})(?:\\s*[\\:\\s|\\,\\s]\\s*(\\d{1,3})(?:\\s*[\\-\\s]\\s*(\\d{1,3}))?)?");
        }
        return regexPassages;
    }

    public Matcher findBibleMatches(String query) {
        return getRegexBible().matcher(query.toLowerCase());
    }

    public Matcher findPassagesMatches(String query) {
        return getRegexPassages().matcher(query.toLowerCase());
    }

    public Bible findBible(String query) {
        Bible bible = null;
        Matcher matcher = findBibleMatches(query);

        if (matcher.find()) {
            bible = bibleRepository.findBible(matcher.group(1));
            LOG.debug("[RESULT] found bible " + bible);
        }
        return bible;
    }

    public List<Passage> process(String query) {
        List<Passage> passages = new LinkedList<>();
        LOG.info("Searching in String: " + query);

        Bible bible = findBible(query);
        if (bible == null) {
            bible = bibleRepository.findBible(bibleRepository.getDefaultBible());
        }
        LOG.debug("Using bible " + bible.getName());

        Matcher matcher = findPassagesMatches(query);
        while (matcher.find()) {

            // input debug print
            LOG.debug("regex result: " + matcher.group() + " with " + matcher.groupCount() + " groups.");
            for (int i = 0; i <= matcher.groupCount(); i++) {
                LOG.debug("------------- " + matcher.group(i));
            }

            if (matcher.groupCount() == 5) {
                Book book = bookRepository.findBook(bible, matcher.group(2));
                LOG.debug("[RESULT] found book " + book + " by input " + matcher.group(2));

                if (book != null) {
                    int chapterNumber = Integer.parseInt(matcher.group(3));
                    Chapter chapter = book.getChapter(chapterNumber);
                    LOG.debug("[RESULT] found chapter " + chapter + " by input " + chapterNumber);

                    if (chapter != null) {
                        // default is all verses of one chapter. e.g.: Ps 1
                        int verseNumberStart = 1;
                        int verseNumberEnd = chapter.getVerses().size();
                        if (matcher.group(4) != null) {
                            verseNumberStart = Integer.parseInt(matcher.group(4));
                            if (matcher.group(5) != null) {
                                // multiple verses. e.g.: Ps 1:4-6 (maximal last verse of chapter and always smaller than verseNumberStart)
                                verseNumberEnd = Integer.min(chapter.getVerses().size(), Integer.parseInt(matcher.group(5)));
                                verseNumberEnd = Integer.max(verseNumberStart, verseNumberEnd);
                            } else {
                                // only one verse. e.g.: Ps 1:1
                                verseNumberEnd = verseNumberStart;
                            }
                        }
                        LOG.debug("[RESULT] found verseNumberStart " + verseNumberStart);
                        LOG.debug("[RESULT] found verseNumberEnd " + verseNumberEnd);

                        Passage passage = new Passage();
                        //TODO: passage.setTitle()
                        passage.setQuery(matcher.group());
                        passage.setBible(bible);
                        passage.setBook(book);
                        passage.setChapter(chapter);
                        for (int i = verseNumberStart; i <= verseNumberEnd; i++) {
                            Verse verse = chapter.getVerse(i);
                            if (verse != null) {
                                passage.getVerses().add(chapter.getVerse(i));
                            } else {
                                LOG.error("Verse " + i + " not found. Input query: " + query);
                            }
                        }
                        passages.add(passage);
                    }
                }
            }
        }
        return passages;
    }
}
