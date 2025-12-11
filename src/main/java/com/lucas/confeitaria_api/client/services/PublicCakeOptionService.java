package com.lucas.confeitaria_api.client.services;

import com.lucas.confeitaria_api.client.entities.PublicCakeOption;
import com.lucas.confeitaria_api.client.repositories.PublicCakeOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicCakeOptionService {

    @Autowired
    private PublicCakeOptionRepository repository;

    public List<PublicCakeOption> request() {
        List<PublicCakeOption> list = new ArrayList<>(repository.findAll());
        return list;
    }
}
