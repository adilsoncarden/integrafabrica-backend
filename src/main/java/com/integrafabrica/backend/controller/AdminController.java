package com.integrafabrica.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
class AdminController {
    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Bienvenido al sistema. Has accedido al panel de administración de IntegraFábrica.");
    }
}