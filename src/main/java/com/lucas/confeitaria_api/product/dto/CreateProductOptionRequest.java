package com.lucas.confeitaria_api.product.dto;

import com.lucas.confeitaria_api.product.entities.ProductOptionType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductOptionRequest {

    @NotNull(message = "O tipo da opção é obrigatório")
    private ProductOptionType type;

    @NotBlank(message = "O nome da opção é obrigatório")
    private String name;

    @NotNull(message = "O preço adicional é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O adicional deve ser maior ou igual a zero")
    private BigDecimal additionalPrice;
}
