package com.lucas.confeitaria_api.product.dto;

import com.lucas.confeitaria_api.product.entities.ProductOptionType;
import com.lucas.confeitaria_api.product.entities.ProductType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotNull(message = "O tipo do produto é obrigatório")
    private ProductType type;

    @NotNull(message = "O preço base é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço base deve ser maior que zero")
    private BigDecimal basePrice;

    @NotEmpty(message = "O produto deve ter pelo menos uma opção")
    private List<CreateProductOptionRequest> options;
}
