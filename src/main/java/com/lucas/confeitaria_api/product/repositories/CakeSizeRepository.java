package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.CakeSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeSizeRepository extends JpaRepository<CakeSize, Long> {
}
