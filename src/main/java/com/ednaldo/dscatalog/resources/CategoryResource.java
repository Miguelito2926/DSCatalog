package com.ednaldo.dscatalog.resources;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.services.CategoryService;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getByCategory(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoryService.getByCategory(id));
    }
}
