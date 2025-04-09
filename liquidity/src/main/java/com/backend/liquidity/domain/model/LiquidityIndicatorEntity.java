package com.backend.liquidity.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "liquidity_indicators")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidityIndicatorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @NotNull
    private BigDecimal totalActivos;

    @NotNull
    private BigDecimal totalPasivos;

    @NotNull
    private BigDecimal ratioLiquidez;
}
