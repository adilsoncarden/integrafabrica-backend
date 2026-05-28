package com.integrafabrica.backend.module.auth.service;

import com.integrafabrica.backend.module.auth.model.User;
import com.integrafabrica.backend.module.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // IMPORTANTE: Spring Security espera "ROLE_ADMIN" para hasRole("ADMIN")
        // o "ADMIN" para hasAuthority("ADMIN").
        // Usaremos el prefijo ROLE_ por buena práctica profesional.
        String roleName = user.getRole().getName().startsWith("ROLE_")
                ? user.getRole().getName()
                : "ROLE_" + user.getRole().getName();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));
    }
}