package com.integrafabrica.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return Map.of("module", "Dashboard", "status", "Access Granted");
    }

    @GetMapping("/categorias")
    public Map<String, String> categorias() {
        return Map.of("module", "Categorias", "action", "Listing all records");
    }

    @GetMapping("/productos")
    public Map<String, String> productos() {
        return Map.of("module", "Productos", "action", "Listing all records");
    }

}