package com.lucas.confeitaria_api.product.services;

import com.lucas.confeitaria_api.product.entities.Recipe;
import com.lucas.confeitaria_api.product.repositories.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    public Recipe insert(Recipe obj) {
        return repository.save(obj);
    }

    public List<Recipe> request() {
        List<Recipe> recipes = new ArrayList<>(repository.findAll());
        return recipes;
    }

    public Recipe update(Long id, Recipe obj) {
        Recipe entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Receita não encontrada."));

        if (obj.getName() != null) entity.setName(obj.getName());
        if (obj.getRecipeCost() != null) entity.setRecipeCost(obj.getRecipeCost());

        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada."));

        repository.deleteByRecipeId(id); // <-- apaga dependências
        repository.delete(recipe);
    }

}
