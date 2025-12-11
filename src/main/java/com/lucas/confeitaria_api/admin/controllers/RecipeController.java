package com.lucas.confeitaria_api.admin.controllers;

import com.lucas.confeitaria_api.admin.entities.Recipe;
import com.lucas.confeitaria_api.admin.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping
    public ResponseEntity<Recipe> insert(@RequestBody Recipe obj) {
        Recipe newRecipe = service.insert(obj);
        return ResponseEntity.ok().body(newRecipe);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> requestAll() {
        List<Recipe> recipes = service.request();
        return ResponseEntity.ok().body(recipes);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Recipe> update(@PathVariable Long id, @RequestBody Recipe obj) {
        Recipe updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
