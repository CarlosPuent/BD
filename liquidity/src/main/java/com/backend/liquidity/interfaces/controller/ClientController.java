package com.backend.liquidity.interfaces.controller;

import com.backend.liquidity.interfaces.dto.ClientResponse;
import com.backend.liquidity.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@CrossOrigin(originPatterns = "*")
public class ClientController {

    private final ClienteService clienteService;

    @GetMapping("/todos")
    public ResponseEntity<List<ClientResponse>> obtenerTodosLosClientes() {
        List<ClientResponse> clientes = clienteService.getAllClients();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/paginados")
    public ResponseEntity<Page<ClientResponse>> obtenerClientesPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ClientResponse> clientes = clienteService.listarTodosLosClientes(page, size);
        return ResponseEntity.ok(clientes);
    }
}
