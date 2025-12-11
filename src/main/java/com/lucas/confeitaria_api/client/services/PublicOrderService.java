package com.lucas.confeitaria_api.client.services;

import com.lucas.confeitaria_api.admin.entities.CakeSize;
import com.lucas.confeitaria_api.admin.entities.RecipeType;
import com.lucas.confeitaria_api.admin.repositories.CakeSizeRepository;
import com.lucas.confeitaria_api.client.dto.PublicOrderRequestDTO;
import com.lucas.confeitaria_api.client.dto.PublicOrderResponseDTO;
import com.lucas.confeitaria_api.client.entities.PublicOrder;
import com.lucas.confeitaria_api.client.repositories.PublicOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicOrderService {

    @Autowired
    PublicOrderRepository repository;

    @Autowired
    CakeSizeRepository cakeSizeRepository;

    public PublicOrder insert(PublicOrder obj) {
        return repository.save(obj);
    }

    public List<PublicOrder> request(){
        List<PublicOrder> list = new ArrayList<>(repository.findAll());
        return list;
    }

    public BigDecimal calculateFinalPrice(PublicOrder order) {
        CakeSize size = order.getSize();        // CakeSize selecionado
        RecipeType type = order.getType();      // tipo de recheio
        return size.getPriceFor(type);          // usa o mapa do CakeSize
    }

    // Cria o pedido
    public PublicOrder createOrder(PublicOrder order) {
        // buscar CakeSize real do banco
        CakeSize size = cakeSizeRepository.findById(order.getSize().getId())
                .orElseThrow(() -> new RuntimeException("CakeSize não encontrado"));

        order.setSize(size); // seta o CakeSize real
        order.setFinalPrice(calculateFinalPrice(order));
        return repository.save(order);
    }

    public PublicOrderResponseDTO createFromRequest(PublicOrderRequestDTO req) {

        CakeSize cakeSize = cakeSizeRepository.findById(req.cakeSizeId())
                .orElseThrow(() -> new RuntimeException("CakeSize não encontrado"));

        PublicOrder order = new PublicOrder();
        order.setSize(cakeSize);
        order.setType(req.type());
        order.setChosenRecheio(req.chosenRecheio());
        order.setChosenMassa(req.chosenMassa());

        BigDecimal finalPrice = cakeSize.getPriceFor(req.type());
        order.setFinalPrice(finalPrice);

        PublicOrder saved = repository.save(order);

        return new PublicOrderResponseDTO(
                saved.getId(),
                cakeSize.getId(),
                cakeSize.getSize(),
                cakeSize.getServings(),
                saved.getType(),
                saved.getChosenRecheio(),
                saved.getChosenMassa(),
                saved.getFinalPrice()
        );
    }

}

