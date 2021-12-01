package org.saikrishna.apps.mynotes.dataaccess;

import org.saikrishna.apps.mynotes.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Long> {
    Set<NoteCategory> findByNameIgnoreCaseLike(String name);
}
