package com.lucas.confeitaria_api.client.repositories;

import com.lucas.confeitaria_api.client.entities.PublicCakeOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicCakeOptionRepository extends JpaRepository<PublicCakeOption, Long> {
}
