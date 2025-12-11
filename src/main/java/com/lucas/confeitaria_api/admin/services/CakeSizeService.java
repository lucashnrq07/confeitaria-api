package com.lucas.confeitaria_api.admin.services;

import com.lucas.confeitaria_api.admin.entities.CakeSize;
import com.lucas.confeitaria_api.admin.repositories.CakeSizeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CakeSizeService {

    @Autowired
    private CakeSizeRepository repository;

    public CakeSize insert(CakeSize obj) {
        return repository.save(obj);
    }

    public List<CakeSize> request() {
        List<CakeSize> sizes = new ArrayList<>(repository.findAll());
        return sizes;
    }

    public CakeSize update(Long id, CakeSize obj) {
        CakeSize entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tamanho não encontrado."));

        if (obj.getSize() != null) entity.setSize(obj.getSize());
        if (obj.getServings() != null) entity.setServings(obj.getServings());
        if (obj.getBasePrice() != null) entity.setBasePrice(obj.getBasePrice());

        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        CakeSize size = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tamanho não encontrado."));

        repository.deleteByRecipeId(id); // <-- apaga dependências
        repository.delete(size);
    }

}
