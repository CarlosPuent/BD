package com.backend.liquidity.interfaces.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponse {
    private Long id;
    private String nivel;
    private String mensaje;
    private LocalDate fecha;
}
