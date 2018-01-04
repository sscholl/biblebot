package org.sscholl.bible.common.model.dto;

/**
 * Created by Simon on 01.10.2017.
 */
public class VerseDTO {

    private Integer id;
    private Integer number;
    private ChapterDTO chapterDTO;
    private String text;

    public VerseDTO() {
    }

    public VerseDTO(int number) {
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

    public ChapterDTO getChapterDTO() {
        return chapterDTO;
    }

    public void setChapterDTO(ChapterDTO chapterDTO) {
        this.chapterDTO = chapterDTO;
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

        VerseDTO verseDTO = (VerseDTO) o;

        return number == verseDTO.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "VerseDTO{" +
                "number=" + number +
                ", chapterDTO=" + chapterDTO +
                ", text='" + text + '\'' +
                '}';
    }
}
