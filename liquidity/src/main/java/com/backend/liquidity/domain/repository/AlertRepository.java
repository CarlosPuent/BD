package com.backend.liquidity.domain.repository;

import com.backend.liquidity.domain.model.AlertEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {
    Page<AlertEntity> findAll(Pageable pageable);
    Page<AlertEntity> findByNivel(String nivel, Pageable pageable);
    List<AlertEntity> findByFechaBetween(LocalDate start, LocalDate end);

}
