package com.integrafabrica.backend.controller;

import com.integrafabrica.backend.dto.MovementDetailRequestDTO;
import com.integrafabrica.backend.dto.MovementDetailResponseDTO;
import com.integrafabrica.backend.service.MovementDetailService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/detalles-movimiento")
public class MovementDetailAdminController {

    private final MovementDetailService movementDetailService;

    public MovementDetailAdminController(MovementDetailService movementDetailService) {
        this.movementDetailService = movementDetailService;
    }

    @PostMapping("/add")
    public ResponseEntity<MovementDetailResponseDTO> addDetail(@Valid @RequestBody MovementDetailRequestDTO request) {
        MovementDetailResponseDTO response = movementDetailService.addDetail(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/movimiento/{movementId}")
    public ResponseEntity<List<MovementDetailResponseDTO>> getDetailsByMovement(@PathVariable Long movementId) {
        return ResponseEntity.ok(movementDetailService.getDetailsByMovementId(movementId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDetailResponseDTO> getDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(movementDetailService.getDetailById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<MovementDetailResponseDTO> updateDetail(
            @PathVariable Long id,
            @Valid @RequestBody MovementDetailRequestDTO request) {
        return ResponseEntity.ok(movementDetailService.updateDetail(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long id) {
        movementDetailService.deleteDetail(id);
        return ResponseEntity.noContent().build();
    }
}