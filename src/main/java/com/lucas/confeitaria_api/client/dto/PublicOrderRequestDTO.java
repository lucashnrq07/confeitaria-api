package com.lucas.confeitaria_api.client.dto;

import com.lucas.confeitaria_api.admin.entities.RecipeType;
import lombok.Getter;

public record PublicOrderRequestDTO(Long cakeSizeId, RecipeType type, String chosenMassa, String chosenRecheio) {
}
