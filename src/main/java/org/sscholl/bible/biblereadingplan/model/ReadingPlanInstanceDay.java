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
}
