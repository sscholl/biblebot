package org.sscholl.bible.common.model.dto;

import org.sscholl.bible.common.model.enums.Testament;

import java.util.*;

/**
 * Created by Simon on 01.10.2017.
 */
public class BookDTO {

    private Integer id;
    private int number;
    private BibleDTO bibleDTO;
    private String name;
    private String germanName;
    private Testament testament;
    private boolean numbered;
    private int numberedNumber;
    private String shortcut;
    private Set<String> shortcuts = new HashSet<>();
    private Map<Integer, ChapterDTO> chapters = new HashMap<>();

    public BookDTO() {
    }

    public BookDTO(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BibleDTO getBibleDTO() {
        return bibleDTO;
    }

    public void setBibleDTO(BibleDTO bibleDTO) {
        this.bibleDTO = bibleDTO;
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

    public Collection<ChapterDTO> getChapters() {
        return chapters.values();
    }

    public ChapterDTO getChapter(int number) {
        return chapters.get(number);
    }

    public ChapterDTO getOrCreateChapter(int number) {
        ChapterDTO obj = getChapter(number);
        if (obj == null) {
            obj = new ChapterDTO(number);
            chapters.put(number, obj);
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO bookDTO = (BookDTO) o;

        return number == bookDTO.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
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
