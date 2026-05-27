package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.MovementDetailRequestDTO;
import com.integrafabrica.backend.dto.MovementDetailResponseDTO;
import java.util.List;

public interface MovementDetailService {
    MovementDetailResponseDTO addDetail(MovementDetailRequestDTO request);

    List<MovementDetailResponseDTO> getDetailsByMovementId(Long movementId);

    MovementDetailResponseDTO getDetailById(Long id);

    MovementDetailResponseDTO updateDetail(Long id, MovementDetailRequestDTO request);

    void deleteDetail(Long id);
}