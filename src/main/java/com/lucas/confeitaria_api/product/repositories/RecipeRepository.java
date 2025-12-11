package com.lucas.confeitaria_api.product.repositories;

import com.lucas.confeitaria_api.product.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
