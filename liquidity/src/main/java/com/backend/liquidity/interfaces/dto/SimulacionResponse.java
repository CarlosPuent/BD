package com.backend.liquidity.interfaces.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimulacionResponse {
    private BigDecimal totalActivos;
    private BigDecimal totalPasivos;
    private BigDecimal ratioLiquidez;
    private String alertaGenerada;
}

