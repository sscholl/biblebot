package org.sscholl.bible.biblereadingplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.biblereadingplan.model.Passage;

/**
 * Created by Simon
 */
public interface PassageRepository extends JpaRepository<Passage, Integer> {
}
