package com.backend.liquidity.services;

import com.backend.liquidity.domain.mapper.DtoMapperAlert;
import com.backend.liquidity.domain.model.AlertEntity;
import com.backend.liquidity.domain.model.ClientEntity;
import com.backend.liquidity.domain.model.LiquidityIndicatorEntity;
import com.backend.liquidity.domain.repository.AlertRepository;
import com.backend.liquidity.domain.repository.ClientRepository;
import com.backend.liquidity.domain.repository.FlowRepository;
import com.backend.liquidity.domain.repository.LiquidityIndicatorRepository;
import com.backend.liquidity.interfaces.dto.AlertResponse;
import com.backend.liquidity.interfaces.dto.SimulacionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LiquidityServiceImpl implements LiquidityService {

    private final ClientRepository clientRepository;
    private final FlowRepository flowRepository;
    private final LiquidityIndicatorRepository indicatorRepository;
    private final AlertRepository alertRepository;

    @Override
    @Transactional
    public SimulacionResponse simularRetiro(int porcentajeRetiro) {

        // 1. Obtener todos los saldos de clientes (activos)
        BigDecimal totalActivos = clientRepository.findAll()
                .stream()
                .map(ClientEntity::getSaldoDisponible)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Calcular retiro simulado
        BigDecimal porcentaje = BigDecimal.valueOf(porcentajeRetiro).divide(BigDecimal.valueOf(100));
        BigDecimal retiroSimulado = totalActivos.multiply(porcentaje);

        // 3. Suponer que los pasivos equivalen al retiro simulado
        BigDecimal totalPasivos = retiroSimulado;

        // 4. Calcular ratio
        BigDecimal ratio = totalPasivos.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : totalActivos.divide(totalPasivos, 2, RoundingMode.HALF_UP);

        // 5. Clasificar alerta
        String nivelAlerta = null;
        String mensajeAlerta = null;

        if (ratio.compareTo(new BigDecimal("1.0")) < 0) {
            nivelAlerta = "CRITICO";
            mensajeAlerta = "¡Riesgo de liquidez crítico!";
        } else if (ratio.compareTo(new BigDecimal("1.2")) < 0) {
            nivelAlerta = "MEDIO";
            mensajeAlerta = "Nivel de liquidez moderado. Se recomienda revisar posiciones.";
        } else if (ratio.compareTo(new BigDecimal("1.5")) < 0) {
            nivelAlerta = "BAJO";
            mensajeAlerta = "Nivel de liquidez aceptable pero con margen estrecho.";
        }

        // 6. Guardar indicador
        LiquidityIndicatorEntity indicador = new LiquidityIndicatorEntity();
        indicador.setFecha(LocalDate.now());
        indicador.setTotalActivos(totalActivos);
        indicador.setTotalPasivos(totalPasivos);
        indicador.setRatioLiquidez(ratio);
        indicatorRepository.save(indicador);

        // 7. Generar alerta si aplica
        if (nivelAlerta != null) {
            AlertEntity alerta = new AlertEntity();
            alerta.setFecha(LocalDate.now());
            alerta.setNivel(nivelAlerta);
            alerta.setMensaje(mensajeAlerta);
            alertRepository.save(alerta);
        }

        return new SimulacionResponse(
                totalActivos,
                totalPasivos,
                ratio,
                mensajeAlerta != null ? mensajeAlerta : "Sin alerta generada"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertResponse> obtenerAlertas() {
        return alertRepository.findAll()
                .stream()
                .map(DtoMapperAlert::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LiquidityIndicatorEntity> obtenerHistorialIndicadores() {
        return indicatorRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlertResponse> obtenerAlertasPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        return alertRepository.findAll(pageable)
                .map(DtoMapperAlert::map);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertResponse> obtenerAlertasPorFecha(LocalDate inicio, LocalDate fin) {
        return alertRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(DtoMapperAlert::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LiquidityIndicatorEntity> obtenerIndicadoresPorFecha(LocalDate inicio, LocalDate fin) {
        return indicatorRepository.findByFechaBetween(inicio, fin);
    }


}
