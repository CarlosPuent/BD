package com.backend.liquidity.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "flows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlowEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ClientEntity cliente;

    @NotNull
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlowType tipo;
    @NotNull
    private LocalDate fecha;
}
