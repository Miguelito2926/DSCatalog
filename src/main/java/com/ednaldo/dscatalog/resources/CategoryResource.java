package com.ednaldo.dscatalog.resources;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.services.CategoryService;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insertCategory(@RequestBody CategoryDTO dto) {
        dto = categoryService.insertCategory(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryService.insertCategory(dto));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getByCategory(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoryService.getByCategory(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDTO dto, @PathVariable Long id) {
        categoryService.updateCategory(dto, id);
        return ResponseEntity.noContent().build();
    }
}
