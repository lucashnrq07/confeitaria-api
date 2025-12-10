package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
