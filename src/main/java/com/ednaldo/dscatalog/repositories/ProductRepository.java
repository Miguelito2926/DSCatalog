package com.ednaldo.dscatalog.repositories;

import com.ednaldo.dscatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "categories")
    @Query("SELECT p FROM Product p")
    Page<Product> findAllWithCategories(Pageable pageable);
}
