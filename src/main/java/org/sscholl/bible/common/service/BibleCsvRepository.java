package org.sscholl.bible.common.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Created by Simon on 01.10.2017.
 */
@Component
public class BibleCsvRepository {

    private static Logger LOGGER = Logger.getLogger(BibleCsvRepository.class);

    @Autowired
    private BibleImportService bibleImportService;

    private Set<BibleDTO> bibleDTOS;

    @PostConstruct
    public Set<BibleDTO> getBibleDTOS() {
        if (bibleDTOS == null) {
            setBibleDTOS(bibleImportService.loadBibleConfig());
        }
        return bibleDTOS;
    }

    public void setBibleDTOS(Set<BibleDTO> bibleDTOS) {
        this.bibleDTOS = bibleDTOS;
    }

    public String getDefaultBible() {
        return "neue";
    }

    public BibleDTO findBible(String idOrShortcut) {
        return getBibleDTOS()
                .stream()
                .filter(b -> b.getKey().equals(idOrShortcut) || b.getShortcuts().contains(idOrShortcut.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public BookDTO findBook(BibleDTO bibleDTO, String idOrShortcut) {
        return bibleDTO.getBooks()
                .stream()
                .filter(b -> String.valueOf(b.getNumber()).equals(idOrShortcut) || b.getShortcuts().contains(idOrShortcut.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public BibleImportService getBibleImportService() {
        return bibleImportService;
    }

    public void setBibleImportService(BibleImportService bibleImportService) {
        this.bibleImportService = bibleImportService;
    }
}
