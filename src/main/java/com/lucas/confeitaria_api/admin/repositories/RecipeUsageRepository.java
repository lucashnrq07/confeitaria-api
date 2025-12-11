package com.lucas.confeitaria_api.admin.repositories;

import com.lucas.confeitaria_api.admin.entities.CakeSize;
import com.lucas.confeitaria_api.admin.entities.RecipeUsage;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RecipeUsageRepository extends JpaRepository<RecipeUsage, Long> {
    List<RecipeUsage> findBySize(CakeSize size);
}
