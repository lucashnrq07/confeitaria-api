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
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        // Atualiza tipo e preço base
        if (request.getType() != null) {
            entity.setType(request.getType());
        }

        if (request.getBasePrice() != null) {
            entity.setBasePrice(request.getBasePrice());
        }

        // Atualização parcial das opções
        if (request.getOptions() != null && !request.getOptions().isEmpty()) {

            for (CreateProductOptionRequest optReq : request.getOptions()) {

                // Se não tem ID → ERRO (pois PATCH deve atualizar, não criar)
                if (optReq.getId() == null) {
                    throw new IllegalArgumentException(
                            "Para atualizar uma option é necessário enviar o ID da option."
                    );
                }

                // Localiza a option existente
                ProductOption existing = entity.getOptions().stream()
                        .filter(o -> o.getId().equals(optReq.getId()))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Option com ID " + optReq.getId() + " não encontrada no produto."
                                )
                        );

                // Atualiza somente campos enviados
                if (optReq.getType() != null)
                    existing.setType(optReq.getType());

                if (optReq.getName() != null)
                    existing.setName(optReq.getName());

                if (optReq.getAdditionalPrice() != null)
                    existing.setAdditionalPrice(optReq.getAdditionalPrice());
            }
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
