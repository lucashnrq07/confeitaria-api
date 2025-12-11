package com.lucas.confeitaria_api.config;

import com.lucas.confeitaria_api.admin.entities.CakeSize;
import com.lucas.confeitaria_api.admin.entities.Recipe;
import com.lucas.confeitaria_api.admin.entities.RecipeType;
import com.lucas.confeitaria_api.admin.entities.RecipeUsage;
import com.lucas.confeitaria_api.admin.repositories.CakeSizeRepository;
import com.lucas.confeitaria_api.admin.repositories.RecipeRepository;
import com.lucas.confeitaria_api.admin.repositories.RecipeUsageRepository;
import com.lucas.confeitaria_api.user.entities.User;
import com.lucas.confeitaria_api.user.entities.UserRole;
import com.lucas.confeitaria_api.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    CakeSizeRepository cakeSizeRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeUsageRepository recipeUsageRepository;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User(null, "cris", encoder.encode("1234"), UserRole.ADMIN);
        User client = new User(null, "lucas", encoder.encode("1234"), UserRole.CLIENT);
        userRepository.saveAll(Arrays.asList(admin, client));

        Recipe recipe1 = new Recipe(null, RecipeType.MASSA, "Chocolate", new BigDecimal("14.62"));
        Recipe recipe2 = new Recipe(null, RecipeType.BRIGADEIRO, "Ninho", new BigDecimal("21.00"));
        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2));

        // cria o CakeSize
        CakeSize cakeSize = new CakeSize();
        cakeSize.setId(null);
        cakeSize.setSize("20cm");
        cakeSize.setServings(30);
        cakeSize.setBasePrice(new BigDecimal("29.79"));

        // cria o mapa de preços por tipo de recheio
        Map<RecipeType, BigDecimal> priceByFilling = new HashMap<>();
        priceByFilling.put(RecipeType.BRIGADEIRO, new BigDecimal("145.00"));
        priceByFilling.put(RecipeType.MOUSSE, new BigDecimal("156.00"));

        cakeSize.setPriceByFilling(priceByFilling);
        cakeSizeRepository .save(cakeSize);

        // testa o método getPriceFor
        BigDecimal priceBrigadeiro = cakeSize.getPriceFor(RecipeType.BRIGADEIRO);
        BigDecimal priceMousse = cakeSize.getPriceFor(RecipeType.MOUSSE);

        System.out.println("Preço Brigadeiro: " + priceBrigadeiro); // 145.00
        System.out.println("Preço Mousse: " + priceMousse);         // 156.00

        RecipeUsage recipeUsage1 = new RecipeUsage(null, cakeSizeRepository.getReferenceById(1L), recipeRepository.getReferenceById(1L), 1.0);
        RecipeUsage recipeUsage2 = new RecipeUsage(null, cakeSizeRepository.getReferenceById(1L), recipeRepository.getReferenceById(2L), 2.0);
        recipeUsageRepository.saveAll(Arrays.asList(recipeUsage1, recipeUsage2));
    }
}
