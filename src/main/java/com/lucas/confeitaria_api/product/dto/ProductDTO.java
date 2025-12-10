package com.lucas.confeitaria_api.product.dto;

import com.lucas.confeitaria_api.product.entities.Product;
import com.lucas.confeitaria_api.product.entities.ProductOption;
import com.lucas.confeitaria_api.product.entities.ProductType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private ProductType type;
    private BigDecimal basePrice;
    private List<ProductOptionDTO> options;

    // construtor que converte da entidade Product
    public ProductDTO(Product entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.type = entity.getType();
        this.basePrice = entity.getBasePrice();

        // converte as opções
        List<ProductOption> opts = entity.getOptions();
        if (opts != null) {
            this.options = opts.stream()
                    .map(ProductOptionDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
