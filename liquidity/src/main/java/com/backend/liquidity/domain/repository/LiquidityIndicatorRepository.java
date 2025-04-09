package com.backend.liquidity.domain.repository;

import com.backend.liquidity.domain.model.LiquidityIndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LiquidityIndicatorRepository extends JpaRepository<LiquidityIndicatorEntity, Long> {
    Optional<LiquidityIndicatorEntity> findTopByOrderByFechaDesc();
    List<LiquidityIndicatorEntity> findByFechaBetween(LocalDate start, LocalDate end);

}
