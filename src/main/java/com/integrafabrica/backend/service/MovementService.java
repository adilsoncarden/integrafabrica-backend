package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.MovementRequestDTO;
import com.integrafabrica.backend.dto.MovementResponseDTO;
import java.util.List;

public interface MovementService {
    MovementResponseDTO createMovement(MovementRequestDTO request);

    List<MovementResponseDTO> getAllMovements();

    List<MovementResponseDTO> getMovementsByType(String type);

    MovementResponseDTO getMovementById(Long id);

    MovementResponseDTO updateMovement(Long id, MovementRequestDTO request);

    void deleteMovement(Long id);
}