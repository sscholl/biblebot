package org.sscholl.bible.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.model.Bible;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Created by simon on 01.10.2017.
 */
@Component
public class BibleRepository {

    Logger LOG = Logger.getLogger(BibleRepository.class);

    @Autowired
    private BibleImportService bibleImportService;

    private Set<Bible> bibles;

    @PostConstruct
    public Set<Bible> getBibles() {
        if (bibles == null) {
            setBibles(bibleImportService.loadBibleConfig());
        }
        return bibles;
    }

    public void setBibles(Set<Bible> bibles) {
        this.bibles = bibles;
    }

    public String getDefaultBible() {
        return "elb";
    }

    public Bible findBible(String idOrShortcut) {
        return getBibles()
                .stream()
                .filter(b -> b.getId().equals(idOrShortcut) || b.getShortcuts().contains(idOrShortcut.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

}
