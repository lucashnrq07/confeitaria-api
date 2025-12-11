package com.lucas.confeitaria_api.client.repositories;

import com.lucas.confeitaria_api.client.entities.PublicOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicOrderRepository extends JpaRepository<PublicOrder, Long> {
}
