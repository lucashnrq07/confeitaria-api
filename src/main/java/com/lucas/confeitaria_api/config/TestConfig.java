package com.lucas.confeitaria_api.config;

import com.lucas.confeitaria_api.product.entities.CakeSize;
import com.lucas.confeitaria_api.product.entities.Recipe;
import com.lucas.confeitaria_api.product.entities.RecipeType;
import com.lucas.confeitaria_api.product.entities.RecipeUsage;
import com.lucas.confeitaria_api.product.repositories.CakeSizeRepository;
import com.lucas.confeitaria_api.product.repositories.RecipeRepository;
import com.lucas.confeitaria_api.product.repositories.RecipeUsageRepository;
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

        CakeSize cakeSize = new CakeSize(null, "20cm", 30, new BigDecimal("29.79"));
        cakeSizeRepository.save(cakeSize);

        RecipeUsage recipeUsage1 = new RecipeUsage(null, cakeSizeRepository.getReferenceById(1L), recipeRepository.getReferenceById(1L), 1.0);
        RecipeUsage recipeUsage2 = new RecipeUsage(null, cakeSizeRepository.getReferenceById(1L), recipeRepository.getReferenceById(2L), 2.0);
        recipeUsageRepository.saveAll(Arrays.asList(recipeUsage1, recipeUsage2));
    }
}
