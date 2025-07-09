package com.ednaldo.dscatalog.resources;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.entities.Category;
import com.ednaldo.dscatalog.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
