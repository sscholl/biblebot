package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.List;

/**
 * A reading plan represents a ordered sequence of multiple reading plan days.
 */
@Entity
@Table
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * e.g.: Complete BibleDTO - 2 Year Plan
     */
    private String name;

    private String bibleKey;

    /**
     * Ordered list of all days that will be processed by
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "plan")
    private List<PlanDay> days;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlanDay> getDays() {
        return days;
    }

    public void setDays(List<PlanDay> days) {
        this.days = days;
    }

    public String getBibleKey() {
        return bibleKey;
    }

    public void setBibleKey(String bibleKey) {
        this.bibleKey = bibleKey;
    }

}
