package com.lucas.confeitaria_api.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public record RecipeUsageDTO(
        String recipeName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        BigDecimal recipeCost,

        Double quantityUsed,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        BigDecimal totalCost
) {}
