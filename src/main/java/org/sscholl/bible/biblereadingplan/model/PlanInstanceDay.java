package org.sscholl.bible.biblereadingplan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Simon
 */
@Entity
@Table
public class PlanInstanceDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isPosted;
    /**
     * depends on planInstance date and number of day. Set on update of planInstance.
     */
    private Date scheduledDate;

    @ManyToOne
    private PlanInstance planInstance;

    @OneToOne
    private PlanDay day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlanInstance getPlanInstance() {
        return planInstance;
    }

    public void setPlanInstance(PlanInstance planInstance) {
        this.planInstance = planInstance;
    }

    public PlanDay getDay() {
        return day;
    }

    public void setDay(PlanDay day) {
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
        return "PlanInstanceDay{" +
                "id=" + id +
                ", isPosted=" + isPosted +
                ", scheduledDate=" + scheduledDate +
                ", planInstance=" + planInstance +
                ", day=" + day +
                '}';
    }
}
