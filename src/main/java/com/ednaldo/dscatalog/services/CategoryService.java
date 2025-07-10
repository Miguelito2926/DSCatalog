package com.ednaldo.dscatalog.services;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.entities.Category;
import com.ednaldo.dscatalog.repositories.CategoryRepository;
import com.ednaldo.dscatalog.services.exceptions.DataBaseIntegrityException;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
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
        Category cat = new Category();
         toEntity(categoryDTO, cat);
        categoryRepository.save(cat);
        return new CategoryDTO(cat);
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

    @Transactional
    public CategoryDTO updateCategory(CategoryDTO dto, Long id) {
        try {

            Category category = categoryRepository.getReferenceById(id);
            toEntity(dto, category);
            categoryRepository.save(category);
            return new CategoryDTO(category);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Not found category: " + id);
        }
    }

    @Transactional
    public void deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Not found category: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseIntegrityException("Referential integrity violation");
        }
    }

    public void toEntity(CategoryDTO dto,Category entity) {
        entity.setName(dto.getName());
    }
}
