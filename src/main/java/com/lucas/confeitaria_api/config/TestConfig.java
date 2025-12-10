package com.lucas.confeitaria_api.config;

import com.lucas.confeitaria_api.product.entity.Product;
import com.lucas.confeitaria_api.product.entity.ProductOption;
import com.lucas.confeitaria_api.product.entity.ProductOptionType;
import com.lucas.confeitaria_api.product.entity.ProductType;
import com.lucas.confeitaria_api.product.repository.ProductOptionRepository;
import com.lucas.confeitaria_api.product.repository.ProductRepository;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        bolo.setOptions(new ArrayList<>()); // MUITO IMPORTANTE!

        // Opções
        ProductOption massa = new ProductOption(null, null, ProductOptionType.MASSA, "Chocolate", new BigDecimal("3.00"));

        ProductOption recheio1 = new ProductOption(null, null, ProductOptionType.RECHEIO, "Brigadeiro", new BigDecimal("5.00"));
        ProductOption recheio2 = new ProductOption(null, null, ProductOptionType.RECHEIO, "Ninho", new BigDecimal("4.00"));

        ProductOption tamanho = new ProductOption(null, null, ProductOptionType.TAMANHO, "20cm", new BigDecimal("10.00"));

        // Relacionando corretamente
        bolo.addOption(massa);
        bolo.addOption(recheio1);
        bolo.addOption(recheio2);
        bolo.addOption(tamanho);

        // Salvar no banco
        productRepository.save(bolo);
        productOptionRepository.saveAll(Arrays.asList(massa, recheio1, recheio2, tamanho));
    }
}
