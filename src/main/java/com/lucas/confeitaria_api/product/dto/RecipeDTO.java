package com.lucas.confeitaria_api.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.confeitaria_api.product.entities.RecipeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record RecipeDTO(
        Long id,
        RecipeType type,
        String name,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        BigDecimal recipeCost) {
}
