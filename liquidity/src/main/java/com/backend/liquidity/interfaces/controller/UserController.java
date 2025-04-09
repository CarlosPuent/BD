package com.backend.liquidity.interfaces.controller;

import com.backend.liquidity.exceptions.ResourceNotFoundException;
import com.backend.liquidity.interfaces.dto.UserRequest;
import com.backend.liquidity.interfaces.dto.UserResponse;
import com.backend.liquidity.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "User Management", description = "APIs for managing users (algunos requieren token JWT)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get current authenticated user",
            description = "Devuelve el usuario autenticado actualmente (requiere token)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(java.security.Principal principal) {
        String username = principal.getName();
        UserResponse user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get all users (público)")
    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        List<UserResponse> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user by ID (público)")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> show(@PathVariable Long id) {
        UserResponse user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario buscado no existe"));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Create a new user (público)")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validation(result));
        }
        UserResponse createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(
            summary = "Update a user (requiere token de admin)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserRequest user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validation(result));
        }
        UserResponse updatedUser = userService.update(user, id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario buscado no existe"));
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Delete a user (requiere token de admin)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario buscado no existe"));
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Map<String, String> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage())
        );
        return errors;
    }
}


