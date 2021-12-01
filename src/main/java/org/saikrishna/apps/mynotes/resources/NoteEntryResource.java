package org.saikrishna.apps.mynotes.resources;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteEntryResource {
    private Long id;
    private String text;
    private String description;
    private LocalDateTime entryDate;
}
