package com.backend.liquidity.domain.repository;

import com.backend.liquidity.domain.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByNombre(String nombre);
}
