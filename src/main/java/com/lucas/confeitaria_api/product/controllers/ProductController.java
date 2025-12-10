package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.dto.CreateProductRequest;
import com.lucas.confeitaria_api.product.dto.ProductDTO;
import com.lucas.confeitaria_api.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody @Validated CreateProductRequest request) {
        ProductDTO saved = service.insert(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> requestProducts() {
        List<ProductDTO> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody CreateProductRequest request) {

        ProductDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
