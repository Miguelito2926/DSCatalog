package com.ednaldo.dscatalog.services;

import com.ednaldo.dscatalog.dto.CategoryDTO;
import com.ednaldo.dscatalog.dto.ProductDTO;
import com.ednaldo.dscatalog.entities.Category;
import com.ednaldo.dscatalog.entities.Product;
import com.ednaldo.dscatalog.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//
//    @Transactional
//    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
//        Category cat = new Category();
//         toEntity(categoryDTO, cat);
//        productRepository.save(cat);
//        return new CategoryDTO(cat);
//    }

    @Transactional
    public Page<ProductDTO> getProducts(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(ProductDTO::new);
    }
//
//    @Transactional
//    public CategoryDTO getByCategory(Long id) throws ResourceNotFoundException {
//        Category category = categoryRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Not found category: " + id));
//
//        return new CategoryDTO(category);
//    }
//
//    @Transactional
//    public CategoryDTO updateCategory(CategoryDTO dto, Long id) {
//        try {
//
//            Category category = categoryRepository.getReferenceById(id);
//            toEntity(dto, category);
//            categoryRepository.save(category);
//            return new CategoryDTO(category);
//
//        } catch (Exception e) {
//            throw new ResourceNotFoundException("Not found category: " + id);
//        }
//    }
//
//    @Transactional
//    public void deleteCategory(Long id) {
//        try {
//            categoryRepository.deleteById(id);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Not found category: " + id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DataBaseIntegrityException("Referential integrity violation");
//        }
//    }

    public void copyDtoToEntity(ProductDTO dto, Product entity) {
        // campos simples
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());

        // categorias: associar por ID existente
        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            Category cat = new Category();
            cat.setId(catDTO.getId());
            entity.getCategories().add(cat);
        }
    }
}
