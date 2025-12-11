package com.lucas.confeitaria_api.admin.controllers;

import com.lucas.confeitaria_api.admin.dto.CakeCostResponseDTO;
import com.lucas.confeitaria_api.admin.services.CostCalculatorService;
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
