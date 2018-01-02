package org.sscholl.bible.biblereadingplan.model;

import org.sscholl.bible.common.model.Passage;

import javax.persistence.*;
import java.util.List;

/**
 * A bible reading plan consists of days. Per day, a reader usually receives a bible passage and/or text to be read.
 */
@Entity
@Table
public class ReadingPlanDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "readingPlanId")
    private ReadingPlan readingPlan;

    /**
     * Some days will not have anything to be read. In this case, this variable is set to true.
     * By default a new day has no content, thus it is free for the reader.
     */
    private boolean isFree = true;

    /**
     * E.g.: Ps1 and Ps2
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passage> passages;

    /**
     * E.g.: "Today we will start the the psalms."
     */
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReadingPlan getReadingPlan() {
        return readingPlan;
    }

    public void setReadingPlan(ReadingPlan readingPlan) {
        this.readingPlan = readingPlan;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassageDTOS(List<Passage> passage) {
        this.passages = passages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
