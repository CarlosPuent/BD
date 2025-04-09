package com.backend.liquidity.services;

import com.backend.liquidity.domain.model.LiquidityIndicatorEntity;
import com.backend.liquidity.interfaces.dto.AlertResponse;
import com.backend.liquidity.interfaces.dto.SimulacionResponse;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface LiquidityService {

    SimulacionResponse simularRetiro(int porcentajeRetiro);
    List<AlertResponse> obtenerAlertas();
    List<LiquidityIndicatorEntity> obtenerHistorialIndicadores();
    Page<AlertResponse> obtenerAlertasPaginadas(int page, int size);
    List<AlertResponse> obtenerAlertasPorFecha(LocalDate inicio, LocalDate fin);
    List<LiquidityIndicatorEntity> obtenerIndicadoresPorFecha(LocalDate inicio, LocalDate fin);


}
