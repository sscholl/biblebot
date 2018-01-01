package org.sscholl.biblereadingplan.model;

import org.sscholl.bible.model.Passage;

import java.util.List;

/**
 * A bible reading plan consists of days. Per day, a reader usually receives a bible passage and/or text to be read.
 */
public class ReadingPlanDay {

    /**
     * Some days will not have anything to be read. In this case, this variable is set to true.
     * By default a new day has no content, thus it is free for the reader.
     */
    private boolean isFree = true;

    /**
     * E.g.: Ps1 and Ps2
     */
    private List<Passage> passages;

    /**
     * E.g.: "Today we will start the the psalms."
     */
    private String text;

}
