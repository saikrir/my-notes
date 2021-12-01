package org.saikrishna.apps.mynotes.resources;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryResource {
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
}
