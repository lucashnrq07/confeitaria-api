package com.lucas.confeitaria_api.admin.dto;

import java.util.List;

public record CakeCostResponseDTO(
        CakeSizeDTO cakeSize,
        List<RecipeUsageDTO> recipes,
        SummaryDTO summary
) {}

