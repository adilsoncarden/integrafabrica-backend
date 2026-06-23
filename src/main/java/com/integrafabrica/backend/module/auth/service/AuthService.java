package com.integrafabrica.backend.module.auth.service;

import com.integrafabrica.backend.config.JwtService;
import com.integrafabrica.backend.module.auth.dto.AuthResponseDTO;
import com.integrafabrica.backend.module.auth.dto.LoginRequestDTO;
import com.integrafabrica.backend.module.auth.model.User;
import com.integrafabrica.backend.module.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsernameOrEmail(request.getIdentifier(), request.getIdentifier())
                .orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(jwtToken);
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getName());
        response.setUserId(user.getId());
        return response;
    }
}