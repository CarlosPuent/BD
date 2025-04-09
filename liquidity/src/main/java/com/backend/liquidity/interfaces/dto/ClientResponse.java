package com.backend.liquidity.interfaces.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private String nombre;
    private BigDecimal saldoDisponible;
}
