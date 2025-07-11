package com.ednaldo.dscatalog.resources;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.dto.ProductDTO;
import com.ednaldo.dscatalog.services.CategoryService;
import com.ednaldo.dscatalog.services.ProductService;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }
//
//    @PostMapping
//    public ResponseEntity<CategoryDTO> insertCategory(@RequestBody CategoryDTO dto) {
//        dto = categoryService.insertCategory(dto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(dto.getId()).toUri();
//        return ResponseEntity.created(uri).body(dto);
//    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getProducts(pageable));
    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<CategoryDTO> getByCategory(@PathVariable Long id) throws ResourceNotFoundException {
//        return ResponseEntity.ok(categoryService.getByCategory(id));
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO dto, @PathVariable Long id) {
//
//        return ResponseEntity.ok(categoryService.updateCategory(dto, id));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//        return ResponseEntity.noContent().build();
//    }
}
