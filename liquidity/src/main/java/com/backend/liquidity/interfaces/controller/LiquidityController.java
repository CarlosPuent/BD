package com.backend.liquidity.interfaces.controller;

import com.backend.liquidity.domain.model.LiquidityIndicatorEntity;
import com.backend.liquidity.interfaces.dto.AlertResponse;
import com.backend.liquidity.interfaces.dto.SimulacionRequest;
import com.backend.liquidity.interfaces.dto.SimulacionResponse;
import com.backend.liquidity.services.LiquidityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Simulador de Liquidez", description = "Endpoints para simular escenarios y ver alertas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/liquidity")
@CrossOrigin(originPatterns = "*")
public class LiquidityController {

    private final LiquidityService liquidityService;

    @Operation(
            summary = "Simular retiro masivo",
            description = "Recibe un porcentaje de retiro (ej: 20) y calcula el nuevo ratio de liquidez"
    )
    @PostMapping("/simulador")
    public ResponseEntity<?> simularRetiro(@Valid @RequestBody SimulacionRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validation(result));
        }

        SimulacionResponse response = liquidityService.simularRetiro(request.getPorcentajeRetiro());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Obtener alertas de liquidez",
            description = "Devuelve todas las alertas generadas por el sistema"
    )
    @GetMapping("/alertas")
    public ResponseEntity<List<AlertResponse>> obtenerAlertas() {
        List<AlertResponse> alertas = liquidityService.obtenerAlertas();
        return ResponseEntity.ok(alertas);
    }

    @Operation(
            summary = "Historial de indicadores de liquidez",
            description = "Muestra el historial de ratios de liquidez registrados",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/indicadores")
    public ResponseEntity<List<LiquidityIndicatorEntity>> historialIndicadores() {
        return ResponseEntity.ok(liquidityService.obtenerHistorialIndicadores());
    }

    private Map<String, String> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage())
        );
        return errors;
    }

    @Operation(summary = "Obtener alertas paginadas",
            description = "Lista alertas de liquidez con paginación",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/alertas/paginadas")
    public ResponseEntity<Page<AlertResponse>> obtenerAlertasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(liquidityService.obtenerAlertasPaginadas(page, size));
    }

    @Operation(summary = "Filtrar alertas por fecha",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/alertas/filtrar")
    public ResponseEntity<List<AlertResponse>> alertasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        return ResponseEntity.ok(liquidityService.obtenerAlertasPorFecha(inicio, fin));
    }

    @Operation(summary = "Filtrar indicadores por fecha",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/indicadores/filtrar")
    public ResponseEntity<List<LiquidityIndicatorEntity>> indicadoresPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        return ResponseEntity.ok(liquidityService.obtenerIndicadoresPorFecha(inicio, fin));
    }

}
