package org.saikrishna.apps.mynotes.controllers;

import org.saikrishna.apps.mynotes.resources.NoteEntryResource;
import org.saikrishna.apps.mynotes.service.CategoryService;
import org.saikrishna.apps.mynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/categories/{categoryId}/note-entries")
public class NoteController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    NoteService noteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteEntryResource> newResource(@Min(1) @PathVariable("categoryId") Long categoryId, @Valid @RequestBody NoteEntryResource noteEntryResource) {
        noteEntryResource.setEntryDate(LocalDateTime.now());
        categoryService.addNoteEntry(categoryId, noteEntryResource);
        return ResponseEntity.created(URI.create("/" + categoryId)).build();
    }

    @PutMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteEntryResource> modifyNoteEntry(@Min(1) @PathVariable("categoryId") Long categoryId,
                                                             @Min(1) @PathVariable("noteId") Long noteId,
                                                             @Valid @RequestBody NoteEntryResource noteEntryResource) {
        noteEntryResource.setEntryDate(LocalDateTime.now());
        noteEntryResource.setId(noteId);
        return ResponseEntity.accepted().body(noteService.modifyNoteEntry(categoryId, noteEntryResource));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<NoteEntryResource>> getAllEntriesInCategory(@Min(1) @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok().body(noteService.allEntriesInCategory(categoryId));
    }
}
