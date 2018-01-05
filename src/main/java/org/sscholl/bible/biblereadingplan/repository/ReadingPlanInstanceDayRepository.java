package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstanceDay;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Simon
 */
public interface ReadingPlanInstanceDayRepository extends JpaRepository<ReadingPlanInstanceDay, Integer> {

    Collection<ReadingPlanInstanceDay> findAllByIsPostedIsFalse();

    Collection<ReadingPlanInstanceDay> findAllByIsPostedIsFalseAndScheduledDateBefore(Date beforeDate);

    Collection<ReadingPlanInstanceDay> findAllByIsPostedIsFalseAndScheduledDateBeforeOrderByScheduledDateAsc(Date beforeDate);

}
