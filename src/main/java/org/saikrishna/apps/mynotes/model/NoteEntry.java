package org.saikrishna.apps.mynotes.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note_entry", schema = "t_my_notes")
@Data
public class NoteEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="entry_id")
    private Long id;

    @Column(name="entry_text", nullable = false)
    private String text;

    @Column(name="entry_description", nullable = false)
    private String description;

    @Column(name="entry_date", nullable = false)
    private LocalDateTime entryDate;

    @ManyToOne
    @JoinColumn(name = "note_category", nullable = false)
    private NoteCategory noteCategory;

}
