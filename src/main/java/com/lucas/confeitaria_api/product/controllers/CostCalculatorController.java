package com.lucas.confeitaria_api.product.controllers;

import com.lucas.confeitaria_api.product.dto.CakeCostResponseDTO;
import com.lucas.confeitaria_api.product.services.CostCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/cost")
@RequiredArgsConstructor
public class CostCalculatorController {

    private final CostCalculatorService costCalculatorService;

    @GetMapping("/{sizeId}")
    public CakeCostResponseDTO calculate(@PathVariable Long sizeId) {
        return costCalculatorService.calculateCost(sizeId);
    }
}
