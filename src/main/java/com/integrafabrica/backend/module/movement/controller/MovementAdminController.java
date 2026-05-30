package com.integrafabrica.backend.module.movement.controller;

import com.integrafabrica.backend.module.movement.dto.MovementRequestDTO;
import com.integrafabrica.backend.module.movement.dto.MovementResponseDTO;
import com.integrafabrica.backend.module.movement.service.MovementService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/movimientos")
public class MovementAdminController {

    private final MovementService movementService;

    public MovementAdminController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping("/create")
    public ResponseEntity<MovementResponseDTO> createMovement(@Valid @RequestBody MovementRequestDTO request) {
        MovementResponseDTO response = movementService.createMovement(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<MovementResponseDTO>> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<MovementResponseDTO>> getMovementsByType(@RequestParam String type) {
        return ResponseEntity.ok(movementService.getMovementsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponseDTO> getMovementById(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<MovementResponseDTO> updateMovement(
            @PathVariable Long id,
            @Valid @RequestBody MovementRequestDTO request) {
        return ResponseEntity.ok(movementService.updateMovement(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }
}