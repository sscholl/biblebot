package org.sscholl.biblereadingplan.model;

import java.util.List;

/**
 * A reading plan represents a ordered sequence of multiple reading plan days.
 */
public class ReadingPlan {

    /**
     * e.g.: Complete Bible - 2 Year Plan
     */
    private String name;

    /**
     * Ordered list of all days that will be processed by
     */
    private List<ReadingPlanDay> days;

}
