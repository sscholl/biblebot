package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * This class represents an instantiation of a reading plan, indicated by the start day.
 */
public class ReadingPlanInstance {

    @OneToOne
    private ReadingPlan readingPlan;

    private Date startTime;

    /**
     * Ordered list of all days that will be processed by
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "readingPlanInstance")
    private List<ReadingPlanInstanceDay> days;

    public ReadingPlan getReadingPlan() {
        return readingPlan;
    }

    public void setReadingPlan(ReadingPlan readingPlan) {
        this.readingPlan = readingPlan;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<ReadingPlanInstanceDay> getDays() {
        return days;
    }

    public void setDays(List<ReadingPlanInstanceDay> days) {
        this.days = days;
    }
}
