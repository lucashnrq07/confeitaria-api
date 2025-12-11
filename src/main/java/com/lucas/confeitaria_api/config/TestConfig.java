package com.lucas.confeitaria_api.config;

import com.lucas.confeitaria_api.user.entities.User;
import com.lucas.confeitaria_api.user.entities.UserRole;
import com.lucas.confeitaria_api.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User(null, "cris", encoder.encode("1234"), UserRole.ADMIN);
        User client = new User(null, "lucas", encoder.encode("1234"), UserRole.CLIENT);
        userRepository.saveAll(Arrays.asList(admin, client));
    }
}
