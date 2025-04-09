package com.backend.liquidity.domain.repository;

import com.backend.liquidity.domain.model.FlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface FlowRepository extends JpaRepository<FlowEntity, Long> {

    List<FlowEntity> findByClienteId(Long clienteId);
    List<FlowEntity> findByFechaBetween(LocalDate start, LocalDate end);
}
