package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import com.lucas.confeitaria_api.product.services.RecipeUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe-usages")
public class RecipeUsageController {

    @Autowired
    private RecipeUsageService service;

    @PostMapping
    public ResponseEntity<RecipeUsage> insert(@RequestBody RecipeUsage obj) {
        RecipeUsage newRecipe = service.insert(obj);
        return ResponseEntity.ok().body(newRecipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeUsage>> requestAll() {
        List<RecipeUsage> recipes = service.request();
        return ResponseEntity.ok().body(recipes);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<RecipeUsage> update(@PathVariable Long id, @RequestBody RecipeUsage obj) {
        RecipeUsage updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
