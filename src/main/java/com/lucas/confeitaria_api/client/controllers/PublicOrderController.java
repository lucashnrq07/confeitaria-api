package com.lucas.confeitaria_api.client.controllers;

import com.lucas.confeitaria_api.client.dto.PublicOrderRequestDTO;
import com.lucas.confeitaria_api.client.dto.PublicOrderResponseDTO;

import com.lucas.confeitaria_api.client.services.PublicOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public-orders")
public class PublicOrderController {

    @Autowired
    private PublicOrderService service;

    @PostMapping
    public ResponseEntity<PublicOrderResponseDTO> insert(@RequestBody @Valid PublicOrderRequestDTO request) {
        PublicOrderResponseDTO response = service.createFromRequest(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<PublicOrderResponseDTO> listOrders() {
        return service.request().stream()
                .map(order -> new PublicOrderResponseDTO(
                        order.getId(),
                        order.getSize().getSize(), // apenas o nome do tamanho
                        order.getType().name(),
                        order.getChosenRecheios(),
                        order.getChosenMassa(),
                        order.getFinalPrice()
                ))
                .toList();
    }

}
