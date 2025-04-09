package com.backend.liquidity.services;

import com.backend.liquidity.interfaces.dto.AlertResponse;
import com.backend.liquidity.interfaces.dto.ClientResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ClienteService {

    List<ClientResponse> getAllClients();
    Page<ClientResponse> listarTodosLosClientes(int page, int size);
}
