package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.PlanDay;

/**
 * Created by Simon
 */
public interface PlanDayRepository extends JpaRepository<PlanDay, Integer> {


}
