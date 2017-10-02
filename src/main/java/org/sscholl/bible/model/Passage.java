package org.sscholl.bible.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by simon on 02.10.2017.
 */
public class Passage {
    private String title;
    private String query;
    private Bible bible;
    private Book book;
    private Chapter chapter;
    private List<Verse> verses = new LinkedList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Bible getBible() {
        return bible;
    }

    public void setBible(Bible bible) {
        this.bible = bible;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "title='" + title + '\'' +
                ", query='" + query + '\'' +
                ", bibleName=" + bible.getName() +
                ", bookName=" + book.getName() +
                ", chapter=" + chapter +
                ", verses=" + verses +
                '}';
    }
}
