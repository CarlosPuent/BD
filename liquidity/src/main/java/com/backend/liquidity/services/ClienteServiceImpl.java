package com.backend.liquidity.services;

import com.backend.liquidity.domain.model.ClientEntity;
import com.backend.liquidity.domain.repository.ClientRepository;
import com.backend.liquidity.interfaces.dto.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponse> getAllClients() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> new ClientResponse(client.getNombre(), client.getSaldoDisponible()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClientResponse> listarTodosLosClientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable).map(client -> new ClientResponse(client.getNombre(), client.getSaldoDisponible()));
    }
}
