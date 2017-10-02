package org.sscholl.bible.model;

import org.sscholl.bible.model.enums.Testament;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by simon on 01.10.2017.
 */
public class Book {
    private int id;
    private String name;
    private Testament testament;
    private boolean numbered;
    private int number;
    private String shortcut;
    private Set<String> shortcuts = new HashSet<>();
    private List<Chapter> chapters = new LinkedList<>();

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public List<Chapter> getChapters() {
        return chapters;
    }

    public Chapter getChapter(int id) {
        return getChapters().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Chapter getOrCreateChapter(int id) {
        Chapter obj = getChapter(id);
        if (obj == null) {
            obj = new Chapter(id);
            getChapters().add(obj);
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testament=" + testament +
                ", numbered=" + numbered +
                ", number=" + number +
                ", shortcut='" + shortcut + '\'' +
                ", shortcuts=" + shortcuts +
                ", chaptersSize=" + chapters.size() +
                '}';
    }
}
