package com.integrafabrica.backend.module.movement.service;

import com.integrafabrica.backend.module.movement.dto.MovementRequestDTO;
import com.integrafabrica.backend.module.movement.dto.MovementResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementService {
    MovementResponseDTO createMovement(MovementRequestDTO request);

    Page<MovementResponseDTO> getAllMovements(Pageable pageable);

    Page<MovementResponseDTO> getMovementsByType(String type, Pageable pageable);

    MovementResponseDTO getMovementById(Long id);

    MovementResponseDTO updateMovement(Long id, MovementRequestDTO request);

    void deleteMovement(Long id);
}