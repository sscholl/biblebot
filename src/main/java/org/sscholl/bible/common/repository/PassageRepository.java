package org.sscholl.bible.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sscholl.bible.common.model.Passage;

/**
 * Created by Simon
 */
public interface PassageRepository extends JpaRepository<Passage, Integer> {
}
