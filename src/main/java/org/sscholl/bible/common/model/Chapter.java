package org.sscholl.bible.common.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Simon on 01.10.2017.
 */
//@Entity
//@Table
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chapter")
    @MapKey(name = "number")
    private Map<Integer, Verse> verses = new HashMap<>();

    public Chapter() {
    }

    public Chapter(Integer number) {
        this.number = number;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Collection<Verse> getVerses() {
        return verses.values();
    }

    public Verse getVerse(int number) {
        return verses.get(number);
    }

    public Verse getOrCreateVerse(int number) {
        Verse obj = getVerse(number);
        if (obj == null) {
            obj = new Verse(number);
            verses.put(number, obj);
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        return number == chapter.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "number=" + number +
                ", versesSize=" + verses.size() +
                '}';
    }
}
