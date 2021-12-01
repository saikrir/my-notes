package org.saikrishna.apps.mynotes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "note_categories", schema = "t_my_notes")
@Entity
@Data
@EqualsAndHashCode
public class NoteCategory {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "category_description", nullable = false)
    @EqualsAndHashCode.Exclude
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "note_category")
    @EqualsAndHashCode.Exclude
    private Set<NoteEntry> noteEntries = new HashSet<>();

    public void addEntry(NoteEntry noteEntry) {
        noteEntry.setNoteCategory(this);
        this.getNoteEntries().add(noteEntry);
    }

    public void removeEntry(NoteEntry noteEntry) {
        if (this.noteEntries.contains(noteEntry)) {
            this.noteEntries.remove(noteEntry);
        }
    }

}