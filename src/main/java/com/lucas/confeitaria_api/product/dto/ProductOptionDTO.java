package com.lucas.confeitaria_api.product.dto;

import com.lucas.confeitaria_api.product.entities.ProductOption;
import com.lucas.confeitaria_api.product.entities.ProductOptionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDTO {

    private Long id;
    private ProductOptionType type;
    private String name;
    private BigDecimal additionalPrice;

    // construtor que converte da entidade
    public ProductOptionDTO(ProductOption entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.type = entity.getType();
        this.name = entity.getName();
        this.additionalPrice = entity.getAdditionalPrice();
    }
}
