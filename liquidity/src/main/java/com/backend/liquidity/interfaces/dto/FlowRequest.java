package com.backend.liquidity.interfaces.dto;

import com.backend.liquidity.domain.model.FlowType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal monto;

    @NotNull(message = "El tipo de flujo es obligatorio")
    private FlowType tipo;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
}
