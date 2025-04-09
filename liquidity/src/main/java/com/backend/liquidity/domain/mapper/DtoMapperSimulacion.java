package com.backend.liquidity.domain.mapper;

import com.backend.liquidity.interfaces.dto.SimulacionResponse;

import java.math.BigDecimal;

public class DtoMapperSimulacion {

    public static SimulacionResponse map(BigDecimal activos, BigDecimal pasivos, BigDecimal ratio, String alerta) {
        return new SimulacionResponse(
                activos,
                pasivos,
                ratio,
                alerta
        );
    }
}
