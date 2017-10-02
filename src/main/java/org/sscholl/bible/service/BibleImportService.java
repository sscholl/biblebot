package org.sscholl.bible.service;

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
import org.sscholl.bible.model.Bible;
import org.sscholl.bible.model.Book;
import org.sscholl.bible.model.Chapter;
import org.sscholl.bible.model.Verse;
import org.sscholl.bible.model.enums.Testament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by simon on 01.10.2017.
 */
@Component
public class BibleImportService {

    Logger LOG = Logger.getLogger(BibleImportService.class);

    @Autowired
    private BookImportService bookImportService;

    public Set<Bible> loadBibleConfig() {
        LOG.debug("loadBibleConfig");
        Set<Bible> bibles = new HashSet<>();
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
                Bible bible = new Bible();
                bible.setId(object.getString("id"));
                bible.setName(object.getString("name"));
                bible.setLanguage(object.getString("language"));
                bible.setDirection(object.getString("direction"));
                JSONArray shortcuts = object.getJSONArray("shortcuts");
                for (int j = 0; j < shortcuts.length(); j++) {
                    bible.getShortcuts().add(shortcuts.getString(j));
                }
                bibles.add(bible);
                bookImportService.loadBookConfig(bible);
                loadBible(bible);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bibles;
    }

    private void loadBible(Bible bible) {
        bible.setFileName("bibles/" + bible.getLanguage() + "__" + bible.getName() + "__" + bible.getId() + "__" + bible.getDirection() + ".txt");

        String line;
        String cvsSplitBy = "\\|\\|";

        try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(bible.getFileName()).getFile()))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] lineContent = line.split(cvsSplitBy);

                Assert.isTrue(lineContent.length == 3 || lineContent.length == 4, "lineContent has wrong length for input line: " + line);

                // Book e.g. 42N or 03O
                Assert.hasLength(lineContent[0], "lineContent[0] is empty");
                int bookNumber = Integer.parseInt(lineContent[0].substring(0, 2));
                String testament = lineContent[0].substring(2, 3);

                // Chapter
                Assert.hasLength(lineContent[1], "lineContent[1] is empty");
                int chapterNumber = Integer.parseInt(lineContent[1]);

                // Verse
                Assert.hasLength(lineContent[2], "lineContent[2] is empty");
                int verseNumber = Integer.parseInt(lineContent[2]);

                // Verse text
                String text;
                if (lineContent.length == 3) {
                    text = "";
                } else {
                    Assert.hasLength(lineContent[3], "lineContent[3] is empty");
                    text = lineContent[3];
                }

                // Create Book object
                Book book = bible.getOrCreateBook(bookNumber);

                // Create Chapter object
                Chapter chapter = book.getOrCreateChapter(chapterNumber);

                // Create Verse object
                Verse verse = chapter.getOrCreateVerse(verseNumber);
                verse.setBook(bookNumber);

                // set testamenr
                if (Objects.equals(testament, "O")) {
                    verse.setTestament(Testament.OT);
                } else if (Objects.equals(testament, "N")) {
                    verse.setTestament(Testament.NT);
                } else {
                    throw new IllegalArgumentException("testament:" + testament + " is not valid.");
                }

                verse.setChapter(chapterNumber);
                verse.setText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
