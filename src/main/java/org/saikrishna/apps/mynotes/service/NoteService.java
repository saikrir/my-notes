package org.saikrishna.apps.mynotes.service;

import org.saikrishna.apps.mynotes.dataaccess.NoteCategoryRepository;
import org.saikrishna.apps.mynotes.dataaccess.NotesRepository;
import org.saikrishna.apps.mynotes.exceptions.NoResultsFoundException;
import org.saikrishna.apps.mynotes.mappers.ModelMapper;
import org.saikrishna.apps.mynotes.model.NoteCategory;
import org.saikrishna.apps.mynotes.model.NoteEntry;
import org.saikrishna.apps.mynotes.resources.NoteEntryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteService {
    @Autowired
    NotesRepository notesRepository;

    @Autowired
    NoteCategoryRepository noteCategoryRepository;

    @Autowired
    ModelMapper modelMapper;

    public Set<NoteEntryResource> allEntriesInCategory(Long categoryId) {
        Set<NoteEntry> noteEntries = notesRepository.noteEntriesInCategoryId(categoryId);
        return noteEntries.stream().map(modelMapper::toNoteEntryResource).collect(Collectors.toSet());
    }

    public Set<NoteEntryResource> allNotes() {
        List<NoteEntry> noteEntries = notesRepository.findAll();
        return noteEntries.stream().map(modelMapper::toNoteEntryResource).collect(Collectors.toSet());
    }

    public NoteEntryResource findNoteEntry(Long noteId) {
        return notesRepository.findById(noteId)
                .map(modelMapper::toNoteEntryResource)
                .orElseThrow(() -> new NoResultsFoundException(NoResultsFoundException.Types.Notes));
    }

    @Transactional
    public NoteEntryResource modifyNoteEntry(Long categoryId, NoteEntryResource noteEntryResource) {
        NoteEntry noteEntry = modelMapper.toNoteEntry(noteEntryResource);
        NoteCategory noteCategory = noteCategoryRepository.findById(categoryId).orElseThrow(() -> new NoResultsFoundException(NoResultsFoundException.Types.Notes));
        noteEntry.setNoteCategory(noteCategory);
        return modelMapper.toNoteEntryResource(notesRepository.saveAndFlush(noteEntry));
    }
}
