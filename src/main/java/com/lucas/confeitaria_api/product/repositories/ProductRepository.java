package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
