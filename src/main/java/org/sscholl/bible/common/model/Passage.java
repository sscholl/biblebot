package org.sscholl.bible.common.model;

import javax.persistence.*;

/**
 * Created by Simon on 02.10.2017.
 */
@Entity
@Table
public class Passage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    private String bibleKey;
    private int bookNumber;
    private int chapterNumber;
    private int verseStart;
    private int verseEnd;

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

    public String getBibleKey() {
        return bibleKey;
    }

    public void setBibleKey(String bibleKey) {
        this.bibleKey = bibleKey;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public int getVerseStart() {
        return verseStart;
    }

    public void setVerseStart(int verseStart) {
        this.verseStart = verseStart;
    }

    public int getVerseEnd() {
        return verseEnd;
    }

    public void setVerseEnd(int verseEnd) {
        this.verseEnd = verseEnd;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bibleKey='" + bibleKey + '\'' +
                ", bookNumber=" + bookNumber +
                ", chapterNumber=" + chapterNumber +
                ", verseStart=" + verseStart +
                ", verseEnd=" + verseEnd +
                '}';
    }
}
