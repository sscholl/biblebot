package org.sscholl.bible.common.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.dto.ChapterDTO;
import org.sscholl.bible.common.model.dto.VerseDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Simon on 01.10.2017.
 */
@Component
public class BibleImportService {

    Logger LOG = Logger.getLogger(BibleImportService.class);

    @Autowired
    private BookImportService bookImportService;

//    @Autowired
//    private BibleRepository bibleRepository;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private ChapterRepository chapterRepository;
//
//    @Autowired
//    private VerseRespository verseRespository;

    public Set<BibleDTO> loadBibleConfig() {
        LOG.debug("loadBibleConfig");
        Set<BibleDTO> bibleDTOS = new HashSet<>();
        String json = null;
        try {
            json = Resources.toString(Resources.getResource("biblesMetadata.json"), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray array;
        try {
            array = new JSONArray(json);
            JSONObject object;
            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
//                if (bibleRepository.countAllByKey(object.getString("key")) < 1) {
                BibleDTO bibleDTO = new BibleDTO();
                bibleDTO.setKey(object.getString("key"));
                bibleDTO.setName(object.getString("name"));
                bibleDTO.setLanguage(object.getString("language"));
                bibleDTO.setDirection(object.getString("direction"));
                JSONArray shortcuts = object.getJSONArray("shortcuts");
                for (int j = 0; j < shortcuts.length(); j++) {
                    bibleDTO.getShortcuts().add(shortcuts.getString(j));
                }
                bibleDTO.setFileName("bibles/" + bibleDTO.getLanguage() + "__" + bibleDTO.getName().replace(" ", "_") + "__" + bibleDTO.getKey() + "__" + bibleDTO.getDirection() + ".txt");

                bibleDTOS.add(bibleDTO);
//                    LOG.debug(bibleDTO);
//                    bibleRepository.save(bibleDTO);

                bookImportService.loadBookConfig(bibleDTO);

                for (BookDTO bookDTO : bibleDTO.getBooks()) {
                    bookDTO.setBibleDTO(bibleDTO);
//                        LOG.debug(bookDTO);
//                        bookRepository.save(bookDTO);
                }

                loadBible(bibleDTO);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        for (BibleDTO bible : bibleDTOS) {
//            bibleRepository.save(bible);
//            for (BookDTO book : bible.getBooks()) {
//                bookRepository.save(book);
//                for (ChapterDTO chapter : book.getChapters()) {
//                    chapterRepository.save(chapter);
//                    verseRespository.save(chapter.getVers());
//                }
//            }
//        }

        return bibleDTOS;
    }

    private void loadBible(BibleDTO bibleDTO) {
        String line;
        String cvsSplitBy = "\\|\\|";

        try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(bibleDTO.getFileName()).getFile()))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] lineContent = line.split(cvsSplitBy);

                Assert.isTrue(lineContent.length == 3 || lineContent.length == 4, "lineContent has wrong length for input line: " + line);

                // BookDTO e.g. 42N or 03O
                Assert.hasLength(lineContent[0], "lineContent[0] is empty");
                int bookNumber = Integer.parseInt(lineContent[0].substring(0, 2));
                String testament = lineContent[0].substring(2, 3);

                // ChapterDTO
                Assert.hasLength(lineContent[1], "lineContent[1] is empty");
                int chapterNumber = Integer.parseInt(lineContent[1]);

                // VerseDTO
                Assert.hasLength(lineContent[2], "lineContent[2] is empty");
                int verseNumber = Integer.parseInt(lineContent[2]);

                // VerseDTO text
                String text;
                if (lineContent.length == 3) {
                    text = "";
                } else {
                    Assert.hasLength(lineContent[3], "lineContent[3] is empty");
                    text = lineContent[3];
                }

                // Create BookDTO object
                BookDTO bookDTO = bibleDTO.getOrCreateBook(bookNumber);

                // Create ChapterDTO object
                ChapterDTO chapterDTO = bookDTO.getOrCreateChapter(chapterNumber);

                // Create VerseDTO object
                VerseDTO verseDTO = chapterDTO.getOrCreateVerse(verseNumber);

                verseDTO.setChapterDTO(chapterDTO);
                verseDTO.setText(text);

                verseDTO.setChapterDTO(chapterDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BookImportService getBookImportService() {
        return bookImportService;
    }

    public void setBookImportService(BookImportService bookImportService) {
        this.bookImportService = bookImportService;
    }
}
