package com.backend.liquidity.interfaces.dto;

import com.backend.liquidity.domain.model.FlowType;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowResponse {
    private Long id;
    private String nombreCliente;
    private BigDecimal monto;
    private FlowType tipo;
    private LocalDate fecha;
}
