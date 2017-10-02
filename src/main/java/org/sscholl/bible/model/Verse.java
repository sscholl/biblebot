package org.sscholl.bible.model;

import org.sscholl.bible.model.enums.Testament;

/**
 * Created by simon on 01.10.2017.
 */
public class Verse {
    private int id;
    private int book;
    private Testament testament;
    private int chapter;
    private String text;

    public Verse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public Testament getTestament() {
        return testament;
    }

    public void setTestament(Testament NT) {
        testament = NT;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verse verse = (Verse) o;

        return id == verse.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Verse{" +
                "id=" + id +
                ", book=" + book +
                ", testament=" + testament +
                ", chapter=" + chapter +
                ", text='" + text + '\'' +
                '}';
    }
}
