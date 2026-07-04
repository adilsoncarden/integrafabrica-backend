package com.integrafabrica.backend.module.location.service;

import com.integrafabrica.backend.module.location.dto.LocationRequestDTO;
import com.integrafabrica.backend.module.location.dto.LocationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationResponseDTO createLocation(LocationRequestDTO request);

    Page<LocationResponseDTO> getAllLocations(Pageable pageable);

    LocationResponseDTO getLocationById(Long id);

    LocationResponseDTO updateLocation(Long id, LocationRequestDTO request);

    void deleteLocation(Long id);
}