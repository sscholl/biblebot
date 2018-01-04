package org.sscholl.bible.common.model.dto;

import java.util.*;

/**
 * Created by Simon on 01.10.2017.
 */
public class BibleDTO {

    private Integer id;
    private String key;
    private String name;
    private String language;
    private String direction;
    private String fileName;
    private Set<String> shortcuts = new HashSet<>();
    private Map<Integer, BookDTO> books = new HashMap<>();

    public BibleDTO() {
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

    public Collection<BookDTO> getBooks() {
        return books.values();
    }

    public BookDTO getBook(int number) {
        return books.get(number);
    }

    public BookDTO getOrCreateBook(int number) {
        BookDTO obj = getBook(number);
        if (obj == null) {
            obj = new BookDTO(number);
            books.put(number, obj);
        }
        return obj;
    }

    @Override
    public String toString() {
        return "BibleDTO{" +
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
