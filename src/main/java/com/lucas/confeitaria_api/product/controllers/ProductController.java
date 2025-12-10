package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.entities.Product;
import com.lucas.confeitaria_api.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        Product newProduct = service.insert(product);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> requestProducts() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
