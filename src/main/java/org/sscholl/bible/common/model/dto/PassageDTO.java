package org.sscholl.bible.common.model.dto;

import java.util.LinkedList;
import java.util.List;

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
    private List<VerseDTO> verses = new LinkedList<>();

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

    public List<VerseDTO> getVerses() {
        return verses;
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
