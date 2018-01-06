package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.List;

/**
 * A bible reading plan consists of days. Per day, a reader usually receives a bible passage and/or text to be read.
 */
@Entity
@Table
public class PlanDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Plan plan;

    /**
     * Some days will not have anything to be read. In this case, this variable is set to true.
     * By default a new day has no content, thus it is free for the reader.
     */
    private boolean isFree = true;

    /**
     * E.g.: Ps1 and Ps2
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "day")
    @OrderColumn
    private List<Passage> passages;

    /**
     * E.g.: "Today we will start the the psalms."
     */
    @Lob
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PlanDay{" +
                "id=" + id +
                ", plan=" + plan +
                ", isFree=" + isFree +
                ", passages=" + passages +
                ", text='" + text + '\'' +
                '}';
    }
}
