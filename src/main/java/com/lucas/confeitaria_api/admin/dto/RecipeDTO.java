package com.lucas.confeitaria_api.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.confeitaria_api.admin.entities.RecipeType;

import java.math.BigDecimal;

public record RecipeDTO(
        Long id,
        RecipeType type,
        String name,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        BigDecimal recipeCost) {
}
