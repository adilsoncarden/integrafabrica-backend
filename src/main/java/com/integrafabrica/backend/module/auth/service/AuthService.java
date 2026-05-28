package com.integrafabrica.backend.module.auth.service;

import com.integrafabrica.backend.config.JwtService;
import com.integrafabrica.backend.module.auth.dto.AuthResponseDTO;
import com.integrafabrica.backend.module.auth.dto.LoginRequestDTO;
import com.integrafabrica.backend.module.auth.model.User;
import com.integrafabrica.backend.module.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsernameOrEmail(request.getIdentifier(), request.getIdentifier())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // Convertimos nuestro usuario a un UserDetails de Spring Security para el token
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));

        String jwtToken = jwtService.generateToken(userDetails);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(jwtToken);
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getName());
        return response;
    }
}