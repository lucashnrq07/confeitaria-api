package com.lucas.confeitaria_api.client.dto;

import com.lucas.confeitaria_api.admin.entities.RecipeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public record PublicOrderRequestDTO(
        Long cakeSizeId,

        @NotNull(message = "É necessário escolher o tipo de recheio")
        RecipeType type,

        @NotBlank(message = "É necessário escolher um sabor de massa")
        String chosenMassa,

        @NotNull(message = "É necessário escolher pelo menos um recheio")
        @Size(min = 1, max = 2, message = "Você só pode escolher 1 ou 2 recheios")
        List<String> chosenRecheios) {
}
