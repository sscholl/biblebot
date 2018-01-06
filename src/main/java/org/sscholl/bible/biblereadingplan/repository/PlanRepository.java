package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.Plan;

/**
 * Created by Simon
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {



}
