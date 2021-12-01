package org.saikrishna.apps.mynotes.controllers;

import org.saikrishna.apps.mynotes.resources.CategoryResource;
import org.saikrishna.apps.mynotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResource> getCategoryById(
            @PathVariable("id") @Min(1) String id) {
        return ResponseEntity.ok().body(categoryService.getById(Long.valueOf(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResource> newCategory(@Valid @RequestBody CategoryResource categoryResource) {
        CategoryResource category = categoryService.createCategory(categoryResource);
        return ResponseEntity.created(URI.create("/" + category.getId())).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResource> modify(@PathVariable("id") @Min(1) String id, @Valid @RequestBody CategoryResource categoryResource) {
        categoryResource.setId(Long.valueOf(id));
        CategoryResource category = categoryService.changeCategory(categoryResource);
        return ResponseEntity.accepted().body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoryResource> removeCategory(@PathVariable("id") @Min(1) String id) {
        categoryService.removeCategory(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{categoryName}")
    public ResponseEntity<Set<CategoryResource>> find(@PathVariable("categoryName") String categoryName) {
        Set<CategoryResource> categoryResources = categoryService.searchCategories(categoryName);
        return ResponseEntity.ok().body(categoryResources);
    }

    @GetMapping
    public ResponseEntity<Set<CategoryResource>> all() {
        Set<CategoryResource> categoryResources = categoryService.allCategories();
        return ResponseEntity.ok().body(categoryResources);
    }
}
