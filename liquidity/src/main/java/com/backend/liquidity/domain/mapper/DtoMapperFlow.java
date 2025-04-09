package com.backend.liquidity.domain.mapper;

import com.backend.liquidity.domain.model.ClientEntity;
import com.backend.liquidity.domain.model.FlowEntity;
import com.backend.liquidity.interfaces.dto.FlowRequest;
import com.backend.liquidity.interfaces.dto.FlowResponse;

public class DtoMapperFlow {

    public static FlowResponse map(FlowEntity flow) {
        return new FlowResponse(
                flow.getId(),
                flow.getCliente().getNombre(),
                flow.getMonto(),
                flow.getTipo(),
                flow.getFecha()
        );
    }

    public static FlowEntity map(FlowRequest request, ClientEntity cliente) {
        return new FlowEntity(
                null,
                cliente,
                request.getMonto(),
                request.getTipo(),
                request.getFecha()
        );
    }
}
