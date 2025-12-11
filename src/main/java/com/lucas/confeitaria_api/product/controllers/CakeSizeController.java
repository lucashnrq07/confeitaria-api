package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.entities.CakeSize;
import com.lucas.confeitaria_api.product.services.CakeSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cake-sizes")
public class CakeSizeController {

    @Autowired
    private CakeSizeService service;

    @PostMapping
    public ResponseEntity<CakeSize> insert(@RequestBody CakeSize obj) {
        CakeSize newSize = service.insert(obj);
        return ResponseEntity.ok().body(newSize);
    }

    @GetMapping
    public ResponseEntity<List<CakeSize>> requestAll() {
        List<CakeSize> sizes = service.request();
        return ResponseEntity.ok().body(sizes);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CakeSize> update(@PathVariable Long id, @RequestBody CakeSize obj) {
        CakeSize updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
