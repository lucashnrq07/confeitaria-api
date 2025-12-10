package com.lucas.confeitaria_api.product.services;

import com.lucas.confeitaria_api.product.dto.CreateProductRequest;
import com.lucas.confeitaria_api.product.dto.ProductDTO;
import com.lucas.confeitaria_api.product.dto.CreateProductOptionRequest;
import com.lucas.confeitaria_api.product.entities.Product;
import com.lucas.confeitaria_api.product.entities.ProductOption;
import com.lucas.confeitaria_api.product.repositories.ProductOptionRepository;
import com.lucas.confeitaria_api.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository optionRepository;

    // INSERT
    public ProductDTO insert(CreateProductRequest request) {

        // 1. Criar Product
        Product product = new Product();
        product.setType(request.getType());
        product.setBasePrice(request.getBasePrice());

        // 2. Converter opções
        List<ProductOption> options = request.getOptions()
                .stream()
                .map(this::convertOptionRequestToEntity)
                .toList();

        // 3. Conectar as opções ao produto
        options.forEach(opt -> opt.setProduct(product));

        product.setOptions(options);

        // 4. Salvar product
        productRepository.save(product);

        // 5. Salvar opções
        optionRepository.saveAll(options);

        return new ProductDTO(product);
    }

    private ProductOption convertOptionRequestToEntity(CreateProductOptionRequest req) {
        ProductOption opt = new ProductOption();
        opt.setType(req.getType());
        opt.setName(req.getName());
        opt.setAdditionalPrice(req.getAdditionalPrice());
        return opt;
    }

    // GET ALL
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
    }

    // UPDATE
    public ProductDTO update(Long id, CreateProductRequest request) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (request.getType() != null) {
            entity.setType(request.getType());
        }

        if (request.getBasePrice() != null) {
            entity.setBasePrice(request.getBasePrice());
        }

        if (request.getOptions() != null) {
            // apagar opções antigas
            optionRepository.deleteAll(entity.getOptions());

            // criar novas opções
            List<ProductOption> newOptions = request.getOptions()
                    .stream()
                    .map(this::convertOptionRequestToEntity)
                    .toList();

            newOptions.forEach(opt -> opt.setProduct(entity));

            entity.setOptions(newOptions);
            optionRepository.saveAll(newOptions);
        }

        productRepository.save(entity);

        return new ProductDTO(entity);
    }

    // DELETE
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        optionRepository.deleteAll(product.getOptions());
        productRepository.delete(product);
    }
}
