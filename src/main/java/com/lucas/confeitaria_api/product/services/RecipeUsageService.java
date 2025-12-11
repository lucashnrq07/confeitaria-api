package com.lucas.confeitaria_api.product.services;

import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import com.lucas.confeitaria_api.product.repositories.RecipeUsageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeUsageService {

    @Autowired
    private RecipeUsageRepository repository;

    public RecipeUsage insert(RecipeUsage obj) {
        return repository.save(obj);
    }

    public List<RecipeUsage> request() {
        List<RecipeUsage> recipes = new ArrayList<>(repository.findAll());
        return recipes;
    }

    public RecipeUsage update(Long id, RecipeUsage obj) {
        RecipeUsage entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não encontrado."));

        if (obj.getRecipe() != null) entity.setRecipe(obj.getRecipe());
        if (obj.getSize() != null) entity.setSize(obj.getSize());
        if (obj.getQuantityUsed() != null) entity.setSize(obj.getSize());

        return repository.save(entity);
    }

    public void delete(Long id) {
        RecipeUsage recipe = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado."));
        repository.delete(recipe);
    }
}
