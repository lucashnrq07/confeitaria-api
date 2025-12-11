package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecipeUsageRepository extends JpaRepository<RecipeUsage, Long> {
}
