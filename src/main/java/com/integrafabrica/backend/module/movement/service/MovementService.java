package com.integrafabrica.backend.module.movement.service;

import java.util.List;

import com.integrafabrica.backend.module.movement.dto.MovementRequestDTO;
import com.integrafabrica.backend.module.movement.dto.MovementResponseDTO;

public interface MovementService {
    MovementResponseDTO createMovement(MovementRequestDTO request);

    List<MovementResponseDTO> getAllMovements();

    List<MovementResponseDTO> getMovementsByType(String type);

    MovementResponseDTO getMovementById(Long id);

    MovementResponseDTO updateMovement(Long id, MovementRequestDTO request);

    void deleteMovement(Long id);
}