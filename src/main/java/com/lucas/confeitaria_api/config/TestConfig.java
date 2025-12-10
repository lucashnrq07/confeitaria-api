package com.lucas.confeitaria_api.config;

import com.lucas.confeitaria_api.product.entities.*;
import com.lucas.confeitaria_api.product.repositories.ProductOptionRepository;
import com.lucas.confeitaria_api.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductOptionRepository productOptionRepository;


    @Override
    public void run(String... args) throws Exception {

        Product bolo = new Product();
        bolo.setType(ProductType.BOLO);
        bolo.setBasePrice(new BigDecimal("20.00"));
        bolo.setSize(CakeSize.VINTE);
        bolo.setOptions(new ArrayList<>());

        // Opções
        ProductOption massa = new ProductOption(null, null, ProductOptionType.MASSA, "Chocolate", new BigDecimal("3.00"));

        ProductOption recheio1 = new ProductOption(null, null, ProductOptionType.RECHEIO, "Brigadeiro", new BigDecimal("5.00"));
        ProductOption recheio2 = new ProductOption(null, null, ProductOptionType.RECHEIO, "Ninho", new BigDecimal("4.00"));

        // Relacionando corretamente
        bolo.addOption(massa);
        bolo.addOption(recheio1);
        bolo.addOption(recheio2);

        // Salvar no banco
        productRepository.save(bolo);
        productOptionRepository.saveAll(Arrays.asList(massa, recheio1, recheio2));
    }
}
