package com.lucas.confeitaria_api.client.controllers;

import com.lucas.confeitaria_api.client.dto.PublicOrderRequestDTO;
import com.lucas.confeitaria_api.client.dto.PublicOrderResponseDTO;
import com.lucas.confeitaria_api.client.entities.PublicOrder;
import com.lucas.confeitaria_api.client.services.PublicOrderService;
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
    public ResponseEntity<PublicOrderResponseDTO> insert(@RequestBody PublicOrderRequestDTO request) {
        PublicOrderResponseDTO response = service.createFromRequest(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PublicOrder>> requestAll() {
        List<PublicOrder> orders = service.request();
        return ResponseEntity.ok().body(orders);
    }
}
