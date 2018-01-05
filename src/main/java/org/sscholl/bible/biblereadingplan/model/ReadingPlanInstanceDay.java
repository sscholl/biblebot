package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Simon
 */
@Entity
@Table
public class ReadingPlanInstanceDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isPosted;
    /**
     * depends on readingPlanInstance date and number of day. Set on update of readingPlanInstance.
     */
    private Date scheduledDate;

    @ManyToOne
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

    public Boolean getPosted() {
        return isPosted;
    }

    public void setPosted(Boolean posted) {
        isPosted = posted;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public String toString() {
        return "ReadingPlanInstanceDay{" +
                "id=" + id +
                ", isPosted=" + isPosted +
                ", scheduledDate=" + scheduledDate +
                ", readingPlanInstance=" + readingPlanInstance +
                ", day=" + day +
                '}';
    }
}
