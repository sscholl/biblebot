package org.sscholl.bible.common.model;

import org.sscholl.bible.common.model.enums.Testament;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Simon on 01.10.2017.
 */
//@Entity
//@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    @ManyToOne
    @JoinColumn(name = "bibleId")
    private Bible bible;
    private String name;
    private String germanName;
    private Testament testament;
    private boolean numbered;
    private int numberedNumber;
    private String shortcut;
    @ElementCollection
    private Set<String> shortcuts = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
    @MapKey(name = "number")
    private Map<Integer, Chapter> chapters = new HashMap<>();

    public Book() {
    }

    public Book(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bible getBible() {
        return bible;
    }

    public void setBible(Bible bible) {
        this.bible = bible;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGermanName() {
        return germanName;
    }

    public void setGermanName(String germanName) {
        this.germanName = germanName;
    }

    public Testament getTestament() {
        return testament;
    }

    public void setTestament(Testament testament) {
        this.testament = testament;
    }

    public boolean isNumbered() {
        return numbered;
    }

    public void setNumbered(boolean numbered) {
        this.numbered = numbered;
    }

    public int getNumberedNumber() {
        return numberedNumber;
    }

    public void setNumberedNumber(int numberedNumber) {
        this.numberedNumber = numberedNumber;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public Set<String> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(Set<String> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public Collection<Chapter> getChapters() {
        return chapters.values();
    }

    public Chapter getChapter(int number) {
        return chapters.get(number);
    }

    public Chapter getOrCreateChapter(int number) {
        Chapter obj = getChapter(number);
        if (obj == null) {
            obj = new Chapter(number);
            chapters.put(number, obj);
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return number == book.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", germanName='" + germanName + '\'' +
                ", testament=" + testament +
                ", numbered=" + numbered +
                ", numberedNumber=" + numberedNumber +
                ", shortcut='" + shortcut + '\'' +
                ", shortcuts=" + shortcuts +
                ", chaptersSize=" + chapters.size() +
                '}';
    }
}
