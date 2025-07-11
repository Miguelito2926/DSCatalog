package com.ednaldo.dscatalog.repositories;

import com.ednaldo.dscatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(value = "product, category")
    Page<Product> findAll(Pageable pageable);
}
