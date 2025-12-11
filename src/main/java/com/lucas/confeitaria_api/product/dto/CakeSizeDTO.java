package com.lucas.confeitaria_api.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public record CakeSizeDTO(
        Long id,
        String size,
        Integer servings,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        BigDecimal basePrice
) {}
