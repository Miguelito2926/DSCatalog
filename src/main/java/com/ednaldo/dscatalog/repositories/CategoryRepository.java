package com.ednaldo.dscatalog.repositories;

import com.ednaldo.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
