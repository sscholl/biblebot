package org.sscholl.bible.common.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Simon on 01.10.2017.
 */
//@Entity
//@Table
public class Bible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String key;
    private String name;
    private String language;
    private String direction;
    private String fileName;
    @ElementCollection
    private Set<String> shortcuts = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bible")
    @MapKey(name = "number")
    private Map<Integer, Book> books = new HashMap<>();

    public Bible() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Collection<Book> getBooks() {
        return books.values();
    }

    public Book getBook(int number) {
        return books.get(number);
    }

    public Book getOrCreateBook(int number) {
        Book obj = getBook(number);
        if (obj == null) {
            obj = new Book(number);
            books.put(number, obj);
        }
        return obj;
    }

    @Override
    public String toString() {
        return "Bible{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", direction='" + direction + '\'' +
                ", fileName='" + fileName + '\'' +
                ", shortcuts=" + shortcuts +
                ", booksSize=" + books.size() +
                '}';
    }
}
