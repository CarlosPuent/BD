package com.backend.liquidity.services;


import com.backend.liquidity.interfaces.dto.UserRequest;
import com.backend.liquidity.interfaces.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();
    Optional<UserResponse> findById(Long id);
    UserResponse save(UserRequest userRequest);
    Optional<UserResponse> update(UserRequest userRequest, Long id);
    void deleteById(Long id);
    Optional<UserResponse> findByUsername(String username);

}