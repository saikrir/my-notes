package org.saikrishna.apps.mynotes.service;

import org.apache.commons.lang.StringUtils;
import org.saikrishna.apps.mynotes.dataaccess.NoteCategoryRepository;
import org.saikrishna.apps.mynotes.exceptions.NoResultsFoundException;
import org.saikrishna.apps.mynotes.exceptions.PreExistingEntityException;
import org.saikrishna.apps.mynotes.mappers.ModelMapper;
import org.saikrishna.apps.mynotes.model.NoteCategory;
import org.saikrishna.apps.mynotes.resources.CategoryResource;
import org.saikrishna.apps.mynotes.resources.NoteEntryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NoteCategoryRepository noteCategoryRepository;


    public Set<CategoryResource> allCategories() {
        List<NoteCategory> categories = noteCategoryRepository.findAll();

        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptySet();
        }
        return toCategoryResources(categories);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryResource createCategory(CategoryResource categoryResource) {
        if (!noteCategoryRepository.findByNameIgnoreCaseLike(categoryResource.getName()).isEmpty()) {
            throw new PreExistingEntityException(categoryResource.getName());
        }

        return saveCategory(categoryResource);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryResource changeCategory(CategoryResource categoryResource) {
        return saveCategory(categoryResource);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCategory(Long id) {
        Optional<NoteCategory> categoryOptional = noteCategoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new NoResultsFoundException(NoResultsFoundException.Types.Category);
        }
        noteCategoryRepository.delete(categoryOptional.get());
    }

    public CategoryResource getById(Long id) {
        return noteCategoryRepository.findById(id)
                .map(modelMapper::toCategoryResource)
                .orElseThrow(() -> new NoResultsFoundException(NoResultsFoundException.Types.Category));
    }

    public Set<CategoryResource> searchCategories(String name) {
        if (StringUtils.isEmpty(name)) {
            return Collections.emptySet();
        }

        Set<NoteCategory> searchResults = noteCategoryRepository.findByNameIgnoreCaseLike(name);
        return toCategoryResources(searchResults);
    }

    private Set<CategoryResource> toCategoryResources(Collection<NoteCategory> categories) {
        return categories.stream().map(modelMapper::toCategoryResource)
                .collect(toSet());
    }

    private CategoryResource saveCategory(CategoryResource categoryResource) {
        NoteCategory noteCategory = modelMapper.toNoteCategory(categoryResource);
        NoteCategory savedCategory = noteCategoryRepository.saveAndFlush(noteCategory);
        return modelMapper.toCategoryResource(savedCategory);
    }

    public CategoryResource addNoteEntry(Long categoryId, NoteEntryResource noteEntryResource) {
        Optional<NoteCategory> noteCategoryOptional = noteCategoryRepository.findById(categoryId);
        if (noteCategoryOptional.isPresent()) {
            NoteCategory noteCategory = noteCategoryOptional.get();
            noteCategory
                    .addEntry(modelMapper.toNoteEntry(noteEntryResource));
            return modelMapper.toCategoryResource(noteCategoryRepository.saveAndFlush(noteCategory));
        }
        throw new NoResultsFoundException(NoResultsFoundException.Types.Category);
    }

    public CategoryResource removeEntry(Long categoryId, NoteEntryResource noteEntryResource) {
        Optional<NoteCategory> noteCategoryOptional = noteCategoryRepository.findById(categoryId);
        if (noteCategoryOptional.isPresent()) {
            NoteCategory noteCategory = noteCategoryOptional.get();
            noteCategory
                    .removeEntry(modelMapper.toNoteEntry(noteEntryResource));
            return modelMapper.toCategoryResource(noteCategoryRepository.saveAndFlush(noteCategory));
        }
        throw new NoResultsFoundException(NoResultsFoundException.Types.Category);
    }
}
