package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;

/**
 * Created by Simon
 */
public class ReadingPlanInstanceDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "readingPlanInstanceId")
    private ReadingPlanInstance readingPlanInstance;

    @OneToOne
    private ReadingPlanDay day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReadingPlanInstance getReadingPlanInstance() {
        return readingPlanInstance;
    }

    public void setReadingPlanInstance(ReadingPlanInstance readingPlanInstance) {
        this.readingPlanInstance = readingPlanInstance;
    }

    public ReadingPlanDay getDay() {
        return day;
    }

    public void setDay(ReadingPlanDay day) {
        this.day = day;
    }
}
