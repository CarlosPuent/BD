package com.backend.liquidity.domain.mapper;

import com.backend.liquidity.domain.model.AlertEntity;
import com.backend.liquidity.interfaces.dto.AlertResponse;

public class DtoMapperAlert {

    public static AlertResponse map(AlertEntity alert) {
        return new AlertResponse(
                alert.getId(),
                alert.getNivel(),
                alert.getMensaje(),
                alert.getFecha()
        );
    }
}
