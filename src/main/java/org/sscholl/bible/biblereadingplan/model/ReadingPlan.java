package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.List;

/**
 * A reading plan represents a ordered sequence of multiple reading plan days.
 */
@Entity
@Table
public class ReadingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * e.g.: Complete Bible - 2 Year Plan
     */
    private String name;

    /**
     * Ordered list of all days that will be processed by
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "readingPlan")
    private List<ReadingPlanDay> days;

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

    public List<ReadingPlanDay> getDays() {
        return days;
    }

    public void setDays(List<ReadingPlanDay> days) {
        this.days = days;
    }
}
