package org.sscholl.bible.common.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.Bible;
import org.sscholl.bible.common.model.Book;
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

    public void loadBookConfig(Bible bible) throws JSONException {
        getMetadata();
        getGermanNames();
        JSONObject numberedShortcuts = metadata.getJSONObject("numberedShortcuts");
        JSONArray numberedAdd = metadata.getJSONArray("numberedAdd");
        JSONArray books = metadata.getJSONArray("books");
        for (int i = 0; i < books.length(); i++) {
            JSONObject object = books.getJSONObject(i);
            int number = Integer.parseInt(object.getString("number"));

            Book book = bible.getOrCreateBook(number);
            if (book != null) {
                book.setName(object.getString("book_name"));
                book.setGermanName(germanNames.getString(book.getName()));

                String testament = object.getString("testament");
                if (Objects.equals(testament, "O")) {
                    book.setTestament(Testament.OT);
                } else if (Objects.equals(testament, "N")) {
                    book.setTestament(Testament.NT);
                } else {
                    throw new IllegalArgumentException("testament:" + testament + " is not valid.");
                }

                book.setShortcut(object.getString("book_ref"));
                JSONArray shortcuts = object.getJSONArray("shortcuts");
                for (int j = 0; j < shortcuts.length(); j++) {
                    book.getShortcuts().add(shortcuts.getString(j));
                }

                if (object.getBoolean("isNumbered")) {
                    book.setNumbered(true);
                    book.setNumberedNumber(object.getInt("numberedNumber"));

                    // adding numbered shortcuts
                    for (int j = 0; j < numberedAdd.length(); j++) {
                        String numberedPrefix = numberedAdd.getString(j);
                        String numberedName = object.getString("numberedName");
                        book.getShortcuts().add(numberedPrefix.replace("X", String.valueOf(book.getNumberedNumber())) + numberedName);

                        JSONArray myNumberedShortcuts = numberedShortcuts.getJSONArray(numberedName);
                        for (int jj = 0; jj < myNumberedShortcuts.length(); jj++) {
                            book.getShortcuts().add(numberedPrefix.replace("X", String.valueOf(book.getNumberedNumber())) + myNumberedShortcuts.getString(jj));
                        }
                    }
                } else {
                    book.setNumbered(false);
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
