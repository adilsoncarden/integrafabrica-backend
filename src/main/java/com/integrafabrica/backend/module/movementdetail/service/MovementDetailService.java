package com.integrafabrica.backend.module.movementdetail.service;

import java.util.List;

import com.integrafabrica.backend.module.movementdetail.dto.MovementDetailRequestDTO;
import com.integrafabrica.backend.module.movementdetail.dto.MovementDetailResponseDTO;

public interface MovementDetailService {
    MovementDetailResponseDTO addDetail(MovementDetailRequestDTO request);

    List<MovementDetailResponseDTO> getDetailsByMovementId(Long movementId);

    MovementDetailResponseDTO getDetailById(Long id);

    MovementDetailResponseDTO updateDetail(Long id, MovementDetailRequestDTO request);

    void deleteDetail(Long id);
}