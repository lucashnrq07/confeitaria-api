package com.lucas.confeitaria_api.admin.repositories;

import com.lucas.confeitaria_api.admin.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Modifying
    @Query("DELETE FROM RecipeUsage ru WHERE ru.recipe.id = :recipeId")
    void deleteByRecipeId(Long recipeId);

}
