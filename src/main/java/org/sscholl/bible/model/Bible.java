package org.sscholl.bible.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by simon on 01.10.2017.
 */
public class Bible {
    private String id;
    private String name;
    private String language;
    private String direction;
    private String fileName;
    private Set<String> shortcuts = new HashSet<>();
    private List<Book> books = new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<String> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(Set<String> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(int i) {
        return getBooks().stream().filter(b -> b.getId() == i).findFirst().orElse(null);
    }

    public Book getOrCreateBook(int i) {
        Book obj = getBook(i);
        if (obj == null) {
            obj = new Book(i);
            getBooks().add(obj);
        }
        return obj;
    }

    @Override
    public String toString() {
        return "Bible{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", direction='" + direction + '\'' +
                ", fileName='" + fileName + '\'' +
                ", shortcuts=" + shortcuts +
                ", booksSize=" + books.size() +
                '}';
    }
}
