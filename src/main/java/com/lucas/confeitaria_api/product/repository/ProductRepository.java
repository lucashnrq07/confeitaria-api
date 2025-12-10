package com.lucas.confeitaria_api.product.repository;

import com.lucas.confeitaria_api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
