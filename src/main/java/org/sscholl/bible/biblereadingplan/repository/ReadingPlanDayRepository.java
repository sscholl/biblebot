package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanDay;

/**
 * Created by Simon
 */
public interface ReadingPlanDayRepository extends JpaRepository<ReadingPlanDay, Integer> {


}
