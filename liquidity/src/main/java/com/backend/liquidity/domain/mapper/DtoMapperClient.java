package com.backend.liquidity.domain.mapper;

import com.backend.liquidity.domain.model.ClientEntity;
import com.backend.liquidity.interfaces.dto.ClientRequest;
import com.backend.liquidity.interfaces.dto.ClientResponse;

public class DtoMapperClient {

    public static ClientResponse map(ClientEntity client) {
        return new ClientResponse(
                client.getNombre(),
                client.getSaldoDisponible()
        );
    }

    public static ClientEntity map(ClientRequest request) {
        return new ClientEntity(
                null, // ID se genera automáticamente
                request.getNombre(),
                request.getSaldoDisponible()
        );
    }
}
