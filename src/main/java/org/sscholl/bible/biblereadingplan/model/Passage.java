package org.sscholl.bible.biblereadingplan.model;

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

    private Integer bookNumber;
    private Integer chapterNumber;
    private Integer verseStart;
    private Integer verseEnd;

    @ManyToOne
    private PlanDay day;

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

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Integer getVerseStart() {
        return verseStart;
    }

    public void setVerseStart(Integer verseStart) {
        this.verseStart = verseStart;
    }

    public Integer getVerseEnd() {
        return verseEnd;
    }

    public void setVerseEnd(Integer verseEnd) {
        this.verseEnd = verseEnd;
    }

    public PlanDay getDay() {
        return day;
    }

    public void setDay(PlanDay day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookNumber=" + bookNumber +
                ", chapterNumber=" + chapterNumber +
                ", verseStart=" + verseStart +
                ", verseEnd=" + verseEnd +
                '}';
    }
}
