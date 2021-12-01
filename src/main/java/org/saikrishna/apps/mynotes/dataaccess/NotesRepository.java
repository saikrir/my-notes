package org.saikrishna.apps.mynotes.dataaccess;

import org.saikrishna.apps.mynotes.model.NoteEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntry, Long> {

    @Query("select noteEntry from NoteEntry noteEntry where noteCategory.id = ?1 ")
    Set<NoteEntry> noteEntriesInCategoryId(Long categoryId);
}
