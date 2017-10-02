package org.sscholl.bible.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by simon on 01.10.2017.
 */
public class Chapter {
    private int id;
    private List<Verse> verses = new LinkedList<>();

    public Chapter(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public Verse getVerse(int id) {
        return getVerses().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Verse getOrCreateVerse(int id) {
        Verse obj = getVerse(id);
        if (obj == null) {
            obj = new Verse(id);
            getVerses().add(obj);
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        return id == chapter.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", versesSize=" + verses.size() +
                '}';
    }
}
