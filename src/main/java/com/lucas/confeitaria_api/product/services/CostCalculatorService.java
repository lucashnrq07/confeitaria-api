package com.lucas.confeitaria_api.product.services;

import com.lucas.confeitaria_api.product.dto.CakeCostResponseDTO;
import com.lucas.confeitaria_api.product.dto.CakeSizeDTO;
import com.lucas.confeitaria_api.product.dto.RecipeUsageDTO;
import com.lucas.confeitaria_api.product.dto.SummaryDTO;
import com.lucas.confeitaria_api.product.entities.CakeSize;
import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import com.lucas.confeitaria_api.product.repositories.CakeSizeRepository;
import com.lucas.confeitaria_api.product.repositories.RecipeUsageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class CostCalculatorService {

    private final CakeSizeRepository cakeSizeRepository;
    private final RecipeUsageRepository recipeUsageRepository;

    private BigDecimal safe(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private double safe(Double value) {
        return value == null ? 0.0 : value;
    }

    @SuppressWarnings("ConstantConditions")
    public CakeCostResponseDTO calculateCost(Long cakeSizeId) {

        // 1. Buscar o tamanho do bolo
        CakeSize cakeSize = cakeSizeRepository.findById(cakeSizeId)
                .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));

        // 2. Buscar todas as receitas relacionadas
        List<RecipeUsage> usages = recipeUsageRepository.findBySize(cakeSize);

        // 3. Montar lista de receitas + custo individual já arredondado
        List<RecipeUsageDTO> usageDTOs = usages.stream().map(u -> {

            BigDecimal recipeCost = safe(u.getRecipe().getRecipeCost());
            double qty = safe(u.getQuantityUsed());

            BigDecimal totalCost = recipeCost
                    .multiply(BigDecimal.valueOf(qty))
                    .setScale(2, RoundingMode.HALF_UP);

            return new RecipeUsageDTO(
                    u.getRecipe().getName(),
                    recipeCost.setScale(2, RoundingMode.HALF_UP),
                    qty,
                    totalCost
            );
        }).toList();

        // 4. Calcular custo total (somando o basePrice)
        BigDecimal totalCost = usageDTOs.stream()
                .map(RecipeUsageDTO::totalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(cakeSize.getBasePrice()) // soma o basePrice
                .setScale(2, RoundingMode.HALF_UP);

        // 5. Preço final ao cliente (exemplo fixo)
        BigDecimal finalPrice = new BigDecimal("145.00");

        // 6. Lucro
        BigDecimal profit = finalPrice.subtract(totalCost)
                .setScale(2, RoundingMode.HALF_UP);

        // 7. Margem
        String profitMargin = finalPrice.compareTo(BigDecimal.ZERO) == 0
                ? "0%"
                : profit.multiply(BigDecimal.valueOf(100))
                .divide(finalPrice, 2, RoundingMode.HALF_UP)
                + "%";

        // 8. Response summary
        SummaryDTO summary = new SummaryDTO(
                totalCost,
                finalPrice,
                profit,
                profitMargin
        );

        // 9. Montar resposta
        return new CakeCostResponseDTO(
                new CakeSizeDTO(
                        cakeSize.getId(),
                        cakeSize.getSize(),
                        cakeSize.getServings(),
                        cakeSize.getBasePrice().setScale(2, RoundingMode.HALF_UP)
                ),
                usageDTOs,
                summary
        );
    }
}

