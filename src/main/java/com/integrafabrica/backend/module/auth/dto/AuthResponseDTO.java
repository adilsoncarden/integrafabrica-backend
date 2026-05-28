package com.integrafabrica.backend.module.auth.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String username;
    private String role;
}