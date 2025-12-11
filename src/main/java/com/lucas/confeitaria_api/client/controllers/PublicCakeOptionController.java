package com.lucas.confeitaria_api.client.controllers;

import com.lucas.confeitaria_api.client.entities.PublicCakeOption;
import com.lucas.confeitaria_api.client.services.PublicCakeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cake-options")
public class PublicCakeOptionController {

    @Autowired
    private PublicCakeOptionService service;

    @GetMapping
    public ResponseEntity<List<PublicCakeOption>> requestAll() {
        List<PublicCakeOption> options = service.request();
        return ResponseEntity.ok().body(options);
    }
}
