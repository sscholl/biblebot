package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.PlanInstanceDay;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Simon
 */
public interface ReadingPlanInstanceDayRepository extends JpaRepository<PlanInstanceDay, Integer> {

    Collection<PlanInstanceDay> findAllByIsPostedIsFalse();

    Collection<PlanInstanceDay> findAllByIsPostedIsFalseAndScheduledDateBefore(Date beforeDate);

    Collection<PlanInstanceDay> findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc(Date beforeDate);

}
