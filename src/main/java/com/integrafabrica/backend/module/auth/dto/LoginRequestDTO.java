package com.integrafabrica.backend.module.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String identifier; // Puede ser username o email
    private String password;
}