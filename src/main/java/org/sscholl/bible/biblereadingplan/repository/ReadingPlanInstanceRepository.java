package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.ReadingPlanInstance;

/**
 * Created by Simon
 */
public interface ReadingPlanInstanceRepository extends JpaRepository<ReadingPlanInstance, Integer> {


}
