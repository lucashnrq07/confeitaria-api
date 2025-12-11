package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeUsageRepository extends JpaRepository<RecipeUsage, Long> {
}
