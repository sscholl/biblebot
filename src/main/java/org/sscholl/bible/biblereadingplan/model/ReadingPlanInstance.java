package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This class represents an instantiation of a reading plan, indicated by the start day.
 */
@Entity
@Table
public class ReadingPlanInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date startDate;

    /**
     * channel name inclusive "#", e.g.: #general
     */
    private String channel;

    @OneToOne
    private Plan plan;

    /**
     * Ordered list of all days that will be processed.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "readingPlanInstance")
    private List<ReadingPlanInstanceDay> days;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<ReadingPlanInstanceDay> getDays() {
        return days;
    }

    public void setDays(List<ReadingPlanInstanceDay> days) {
        this.days = days;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
