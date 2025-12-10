package com.lucas.confeitaria_api.product.services;


import com.lucas.confeitaria_api.product.dto.CreateProductOptionRequest;
import com.lucas.confeitaria_api.product.dto.ProductOptionDTO;
import com.lucas.confeitaria_api.product.entities.ProductOption;
import com.lucas.confeitaria_api.product.repositories.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionService {

    @Autowired
    private ProductOptionRepository optionRepository;

    // INSERT
    public ProductOptionDTO insert(CreateProductOptionRequest request) {

        // 1. Criar Product
        ProductOption option = new ProductOption();
        option.setType(request.getType());
        option.setAdditionalPrice(request.getAdditionalPrice());
        option.setName(request.getName());

        // 2. Salvar
        optionRepository.save(option);

        return new ProductOptionDTO(option);
    }

    // GET ALL
    public List<ProductOptionDTO> getAllProducts() {
        return optionRepository.findAll()
                .stream()
                .map(ProductOptionDTO::new)
                .toList();
    }

    // UPDATE
    public ProductOptionDTO update(Long id, CreateProductOptionRequest request) {

        ProductOption entity = optionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Opção não encontrada."));

        // Atualiza tipo e preço base
        if (request.getType() != null) {
            entity.setType(request.getType());
        }
        if (request.getAdditionalPrice() != null) {
            entity.setAdditionalPrice(request.getAdditionalPrice());
        }
        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        optionRepository.save(entity);
        return new ProductOptionDTO(entity);
    }

    // DELETE
    public void deleteOption(Long id) {
        ProductOption option = optionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Opção não encontrada."));
        optionRepository.delete(option);
    }
}
