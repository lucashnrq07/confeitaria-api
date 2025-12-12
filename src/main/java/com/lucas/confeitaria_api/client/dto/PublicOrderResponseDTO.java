package com.lucas.confeitaria_api.client.dto;

import java.math.BigDecimal;
import java.util.List;

public record PublicOrderResponseDTO(
        Long id,
        String size,
        String type,
        List<String> chosenRecheios,
        String chosenMassa,
        BigDecimal finalPrice) {
}
