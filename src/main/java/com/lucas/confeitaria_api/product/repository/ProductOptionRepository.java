package com.lucas.confeitaria_api.product.repository;

import com.lucas.confeitaria_api.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
