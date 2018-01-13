package org.sscholl.bible.common.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.enums.Testament;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Simon on 01.10.2017.
 */
@Component
public class BookImportService {

    private JSONObject metadata;
    private JSONObject germanNames;

    public void loadBookConfig(BibleDTO bibleDTO) throws JSONException {
        getMetadata();
        getGermanNames();
        JSONObject numberedShortcuts = metadata.getJSONObject("numberedShortcuts");
        JSONArray numberedAdd = metadata.getJSONArray("numberedAdd");
        JSONArray books = metadata.getJSONArray("books");
        for (int i = 0; i < books.length(); i++) {
            JSONObject object = books.getJSONObject(i);
            int number = Integer.parseInt(object.getString("number"));

            BookDTO bookDTO = bibleDTO.getOrCreateBook(number);
            if (bookDTO != null) {
                bookDTO.setName(object.getString("book_name"));
                bookDTO.setGermanName(germanNames.getString(bookDTO.getName()));

                String testament = object.getString("testament");
                if (Objects.equals(testament, "O")) {
                    bookDTO.setTestament(Testament.OT);
                } else if (Objects.equals(testament, "N")) {
                    bookDTO.setTestament(Testament.NT);
                } else {
                    throw new IllegalArgumentException("testament:" + testament + " is not valid.");
                }

                bookDTO.setShortcut(object.getString("book_ref"));
                JSONArray shortcuts = object.getJSONArray("shortcuts");
                bookDTO.getShortcuts().add(bookDTO.getName().toLowerCase());
                bookDTO.getShortcuts().add(bookDTO.getGermanName().toLowerCase());
                for (int j = 0; j < shortcuts.length(); j++) {
                    bookDTO.getShortcuts().add(shortcuts.getString(j));
                }

                if (object.getBoolean("isNumbered")) {
                    bookDTO.setNumbered(true);
                    bookDTO.setNumberedNumber(object.getInt("numberedNumber"));

                    // adding numbered shortcuts
                    for (int j = 0; j < numberedAdd.length(); j++) {
                        String numberedPrefix = numberedAdd.getString(j);
                        String numberedName = object.getString("numberedName");
                        bookDTO.getShortcuts().add(numberedPrefix.replace("X", String.valueOf(bookDTO.getNumberedNumber())) + numberedName);

                        JSONArray myNumberedShortcuts = numberedShortcuts.getJSONArray(numberedName);
                        for (int jj = 0; jj < myNumberedShortcuts.length(); jj++) {
                            bookDTO.getShortcuts().add(numberedPrefix.replace("X", String.valueOf(bookDTO.getNumberedNumber())) + myNumberedShortcuts.getString(jj));
                        }
                    }
                } else {
                    bookDTO.setNumbered(false);
                }

                for (String shortcut : bookDTO.getShortcuts()) {
                    if (shortcut.length() < 2) {
                        throw new IllegalArgumentException("Shortcut " + shortcut + " can not be shorter than 2 chars.");
                    }
                }
            }
        }
    }

    private JSONObject getMetadata() {
        if (metadata == null) {
            String json = null;
            try {
                json = Resources.toString(Resources.getResource("booksMetadata.json"), Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                metadata = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return metadata;
    }

    private JSONObject getGermanNames() {
        if (germanNames == null) {
            String json = null;
            try {
                json = Resources.toString(Resources.getResource("bookNames-german.json"), Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                germanNames = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return germanNames;
    }

}
