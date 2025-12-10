package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.dto.CreateProductOptionRequest;
import com.lucas.confeitaria_api.product.dto.ProductOptionDTO;
import com.lucas.confeitaria_api.product.entities.ProductOption;
import com.lucas.confeitaria_api.product.services.ProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/options")
public class ProductOptionController {

    @Autowired
    ProductOptionService service;

    @PostMapping
    public ResponseEntity<ProductOptionDTO> insert(@RequestBody @Validated CreateProductOptionRequest request) {
        ProductOptionDTO saved = service.insert(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ProductOptionDTO>> requestOptions() {
        List<ProductOptionDTO> options = service.getAllProducts();
        return ResponseEntity.ok(options);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductOptionDTO> updateOption(
            @PathVariable Long id,
            @RequestBody CreateProductOptionRequest request) {

        ProductOptionDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        service.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
