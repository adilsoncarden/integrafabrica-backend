package com.integrafabrica.backend.module.auth.controller;

import com.integrafabrica.backend.module.auth.dto.AuthResponseDTO;
import com.integrafabrica.backend.module.auth.dto.LoginRequestDTO;
import com.integrafabrica.backend.module.auth.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}