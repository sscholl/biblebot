package org.sscholl.bible.common.model;

import javax.persistence.*;

/**
 * Created by Simon on 01.10.2017.
 */
//@Entity
//@Table
public class Verse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "chapterId")
    private Chapter chapter;

    @Lob
    private String text;

    public Verse() {
    }

    public Verse(int number) {
        this.number = number;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
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

        return number == verse.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Verse{" +
                "number=" + number +
                ", chapter=" + chapter +
                ", text='" + text + '\'' +
                '}';
    }
}
