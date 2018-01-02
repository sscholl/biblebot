package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.ReadingPlan;

/**
 * Created by Simon
 */
public interface ReadingPlanRepository extends JpaRepository<ReadingPlan, Integer> {
}
