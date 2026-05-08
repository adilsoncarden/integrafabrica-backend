package com.integrafabrica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Respuesta que contiene el token JWT generado
@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
}
