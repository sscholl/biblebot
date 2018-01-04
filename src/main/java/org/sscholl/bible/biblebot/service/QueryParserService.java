package org.sscholl.bible.biblebot.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.*;
import org.sscholl.bible.common.service.BibleCsvRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Simon on 02.10.2017.
 */
@Component
public class QueryParserService {

    private static Logger LOGGER = Logger.getLogger(QueryParserService.class);

    @Autowired
    private BibleCsvRepository bibleCsvRepository;

    private Pattern regexBibles;

    private Pattern regexPassages;

    @PostConstruct
    public Pattern getRegexBible() {
        if (regexBibles == null) {
            Set<String> shortcutsBible = bibleCsvRepository.getBibleDTOS()
                    .stream()
                    .map(BibleDTO::getShortcuts)
                    .reduce((strings, strings2) -> Stream.concat(strings.stream(), strings2.stream()).collect(Collectors.toSet()))
                    .orElse(new HashSet<>());

            regexBibles = Pattern.compile(" (" + shortcutsBible.stream().reduce((s, s2) -> s + "|" + s2).orElse("") + ") ");
        }
        return regexBibles;
    }

    @PostConstruct
    public Pattern getRegexPassages() {
        if (regexPassages == null) {
            Set<String> shortcutsBooks = bibleCsvRepository.findBible(bibleCsvRepository.getDefaultBible())
                    .getBooks()
                    .stream()
                    .map(BookDTO::getShortcuts)
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

    public BibleDTO findBible(String query) {
        BibleDTO bibleDTO = null;
        Matcher matcher = findBibleMatches(query);

        if (matcher.find()) {
            bibleDTO = bibleCsvRepository.findBible(matcher.group(1));
            LOGGER.debug("[RESULT] found bibleDTO " + bibleDTO);
        }
        return bibleDTO;
    }

    public List<PassageDTO> process(String query) {
        List<PassageDTO> passageDTOS = new LinkedList<>();
        LOGGER.info("Searching in String: " + query);

        BibleDTO bibleDTO = findBible(query);
        if (bibleDTO == null) {
            bibleDTO = bibleCsvRepository.findBible(bibleCsvRepository.getDefaultBible());
        }
        LOGGER.debug("Using bibleDTO " + bibleDTO.getName());

        Matcher matcher = findPassagesMatches(query);
        while (matcher.find()) {

            // input debug print
            LOGGER.debug("regex result: " + matcher.group() + " with " + matcher.groupCount() + " groups.");
            for (int i = 0; i <= matcher.groupCount(); i++) {
                LOGGER.debug("------------- " + matcher.group(i));
            }

            if (matcher.groupCount() == 5) {
                BookDTO bookDTO = bibleCsvRepository.findBook(bibleDTO, matcher.group(2));
                LOGGER.debug("[RESULT] found bookDTO " + bookDTO + " by input " + matcher.group(2));

                if (bookDTO != null) {
                    int chapterNumber = Integer.parseInt(matcher.group(3));
                    ChapterDTO chapterDTO = bookDTO.getChapter(chapterNumber);
                    LOGGER.debug("[RESULT] found chapterDTO " + chapterDTO + " by input " + chapterNumber);

                    if (chapterDTO != null) {
                        // default is all vers of one chapterDTO. e.g.: Ps 1
                        int verseNumberStart = 1;
                        int verseNumberEnd = chapterDTO.getVerses().size();
                        if (matcher.group(4) != null) {
                            verseNumberStart = Integer.parseInt(matcher.group(4));
                            if (matcher.group(5) != null) {
                                // multiple vers. e.g.: Ps 1:4-6 (maximal last verse of chapterDTO and always smaller than verseNumberStart)
                                verseNumberEnd = Integer.min(chapterDTO.getVerses().size(), Integer.parseInt(matcher.group(5)));
                                verseNumberEnd = Integer.max(verseNumberStart, verseNumberEnd);
                            } else {
                                // only one verse. e.g.: Ps 1:1
                                verseNumberEnd = verseNumberStart;
                            }
                        }
                        LOGGER.debug("[RESULT] found verseNumberStart " + verseNumberStart);
                        LOGGER.debug("[RESULT] found verseNumberEnd " + verseNumberEnd);

                        List<VerseDTO> verses = new LinkedList<>();
                        for (int i = verseNumberStart; i <= verseNumberEnd; i++) {
                            VerseDTO verseDTO = chapterDTO.getVerse(i);

                            if (verseDTO != null) {
                                verses.add(chapterDTO.getVerse(i));
                            } else {
                                LOGGER.error("VerseDTO " + i + " not found. Input query: " + query);
                            }
                        }

                        if (!verses.isEmpty()) {
                            PassageDTO passageDTO = new PassageDTO();
                            passageDTO.setTitle(bookDTO.getGermanName() + " " + chapterDTO.getNumber());
                            passageDTO.setQuery(matcher.group());
                            passageDTO.setBibleDTO(bibleDTO);
                            passageDTO.setBookDTO(bookDTO);
                            passageDTO.setChapterDTO(chapterDTO);
                            passageDTO.getVerses().addAll(verses);
                            passageDTOS.add(passageDTO);
                        }
                    }
                }
            }
        }
        LOGGER.debug("generate passageDTOS: " + passageDTOS);
        return passageDTOS;
    }
}
