package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.*;
import com.integrafabrica.backend.model.User;
import com.integrafabrica.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsernameOrEmail(request.getIdentifier(), request.getIdentifier())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken("SIMULATED_JWT_TOKEN_FOR_NOW"); // Aquí integrarías la generación real de JWT
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getName());
        return response;
    }
}