package com.lucas.confeitaria_api.product.services;

import com.lucas.confeitaria_api.product.entities.Product;
import com.lucas.confeitaria_api.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    // inserir produto
    public Product insert(Product product) {
        return repository.save(product);
    }

    // buscar todos os produtos
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>(repository.findAll());
        return products;
    }

    // atualizar um produto
    public Product update(Long id, Product product) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        if (product.getType() != null) {
            entity.setType(product.getType());
        }
        if (product.getOptions() != null) {
            entity.setOptions(product.getOptions());
        }
        if (product.getBasePrice() != null) {
            entity.setBasePrice(product.getBasePrice());
        }

        return repository.save(entity);
    }

    // deletar um produto
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
        repository.delete(product);
    }
}
