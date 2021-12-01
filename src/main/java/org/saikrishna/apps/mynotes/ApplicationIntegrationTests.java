package org.saikrishna.apps.mynotes;

import lombok.extern.slf4j.Slf4j;
import org.saikrishna.apps.mynotes.resources.CategoryResource;
import org.saikrishna.apps.mynotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class ApplicationIntegrationTests implements CommandLineRunner {

    @Autowired
    CategoryService categoryService;

    @Override
    public void run(String ...args) {
        Set<CategoryResource> categoryResources = categoryService.allCategories();
        log.info(" -> " + categoryResources);
//        NoteCategory noteCategory = new NoteCategory();
//        noteCategory.setName("Urls");
//        noteCategory.setDescription("Set Of Urls");
//        noteCategory.setNoteEntries(new ArrayList<>());
//
//        NoteEntry noteEntry = new NoteEntry();
//        noteEntry.setText("https://www.google.com");
//        noteEntry.setDescription("Google Search");
//        noteEntry.setEntryDate(LocalDateTime.now());
//        noteCategory.getNoteEntries().add(noteEntry);
//        noteEntry.setNoteCategory(noteCategory);
//
//        noteCategoryRepository.saveAndFlush(noteCategory);
    }
}
