package com.ednaldo.dscatalog.repositories;

import com.ednaldo.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
