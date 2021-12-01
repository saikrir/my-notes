package org.saikrishna.apps.mynotes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.saikrishna.apps.mynotes.resources.CategoryResource;
import org.saikrishna.apps.mynotes.resources.NoteEntryResource;
import org.saikrishna.apps.mynotes.model.NoteCategory;
import org.saikrishna.apps.mynotes.model.NoteEntry;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {
    CategoryResource toCategoryResource(NoteCategory noteCategory);

    @Mapping(target = "categoryDTO.noteEntries", ignore = true)
    NoteCategory toNoteCategory(CategoryResource categoryResource);

    NoteEntryResource toNoteEntryResource(NoteEntry noteEntry);

    NoteEntry toNoteEntry(NoteEntryResource noteEntryResource);
}
