package com.ednaldo.dscatalog.services;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.entities.Category;
import com.ednaldo.dscatalog.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<CategoryDTO> getCategories(Pageable pageable) {
        Page<Category> list = categoryRepository.findAll(pageable);
        return list.map(CategoryDTO::new);
    }
}
