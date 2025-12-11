package com.lucas.confeitaria_api.client.dto;

import com.lucas.confeitaria_api.admin.entities.RecipeType;

import java.math.BigDecimal;

public record PublicOrderResponseDTO(Long id, Long cakeSizeId, String cakeSizeName, Integer servings, RecipeType type, String chosenRecheio, String chosenMassa, BigDecimal finalPrice) {
}
