package org.sscholl.bible.common.model.dto;

import org.sscholl.bible.common.model.Bible;
import org.sscholl.bible.common.model.Book;
import org.sscholl.bible.common.model.Chapter;
import org.sscholl.bible.common.model.Verse;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon on 02.10.2017.
 */
public class PassageDTO {

    private Integer id;
    private String title;
    private String query;
    private Bible bible;
    private Book book;
    private Chapter chapter;
    private List<Verse> verses = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "PassageDTO{" +
                "title='" + title + '\'' +
                ", query='" + query + '\'' +
                ", bibleName=" + bible.getName() +
                ", bookName=" + book.getName() +
                ", chapter=" + chapter +
                ", verses=" + verses +
                '}';
    }
}
