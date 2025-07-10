package com.ednaldo.dscatalog.services;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.entities.Category;
import com.ednaldo.dscatalog.repositories.CategoryRepository;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
        Category cat = toEntity(categoryDTO);
        var categorySave = categoryRepository.save(cat);
        return new CategoryDTO(categorySave);
    }

    @Transactional
    public Page<CategoryDTO> getCategories(Pageable pageable) {
        Page<Category> list = categoryRepository.findAll(pageable);
        return list.map(CategoryDTO::new);
    }

    @Transactional
    public CategoryDTO getByCategory(Long id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found category: " + id));

        return new CategoryDTO(category);
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
