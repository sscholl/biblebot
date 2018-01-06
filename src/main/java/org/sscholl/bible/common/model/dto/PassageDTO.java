package org.sscholl.bible.common.model.dto;

import java.util.Collection;
import java.util.TreeMap;

/**
 * Created by Simon on 02.10.2017.
 */
public class PassageDTO {

    private Integer id;
    private String title;
    private String query;
    private BibleDTO bibleDTO;
    private BookDTO bookDTO;
    private ChapterDTO chapterDTO;
    private TreeMap<Integer, VerseDTO> verses = new TreeMap<>();

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public BibleDTO getBibleDTO() {
        return bibleDTO;
    }

    public void setBibleDTO(BibleDTO bibleDTO) {
        this.bibleDTO = bibleDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public ChapterDTO getChapterDTO() {
        return chapterDTO;
    }

    public void setChapterDTO(ChapterDTO chapterDTO) {
        this.chapterDTO = chapterDTO;
    }

    public Collection<VerseDTO> getVerses() {
        return verses.values();
    }

    public VerseDTO getFirstVerse() {
        if (verses.firstEntry() != null) {
            return verses.firstEntry().getValue();
        }
        return null;
    }

    public VerseDTO getLastVerse() {
        if (verses.lastEntry() != null) {
            return verses.lastEntry().getValue();
        }
        return null;
    }

    public VerseDTO getVerse(int number) {
        return verses.get(number);
    }

    public void addVerse(VerseDTO verseDTO) {
        verses.put(verseDTO.getNumber(), verseDTO);
    }

    @Override
    public String toString() {
        return "PassageDTO{" +
                "title='" + title + '\'' +
                ", query='" + query + '\'' +
                ", bibleName=" + bibleDTO.getName() +
                ", bookName=" + bookDTO.getName() +
                ", chapterDTO=" + chapterDTO +
                ", verses=" + verses +
                '}';
    }
}
