package com.backend.liquidity.domain.mapper;

import com.backend.liquidity.domain.model.UserEntity;
import com.backend.liquidity.interfaces.dto.UserResponse;
import java.util.stream.Collectors;

public class DtoMapperUser {

    public static UserResponse map(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream()
                        .anyMatch(role -> "ROLE_ADMIN".equals(role.getName())),
                user.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList())
        );
    }
}

