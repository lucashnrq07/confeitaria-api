package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.CakeSize;
import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface RecipeUsageRepository extends JpaRepository<RecipeUsage, Long> {
    List<RecipeUsage> findBySize(CakeSize size);
}
