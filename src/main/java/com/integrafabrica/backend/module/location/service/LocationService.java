package com.integrafabrica.backend.module.location.service;

import java.util.List;

import com.integrafabrica.backend.module.location.dto.LocationRequestDTO;
import com.integrafabrica.backend.module.location.dto.LocationResponseDTO;

public interface LocationService {
    LocationResponseDTO createLocation(LocationRequestDTO request);

    List<LocationResponseDTO> getAllLocations();

    LocationResponseDTO getLocationById(Long id);

    LocationResponseDTO updateLocation(Long id, LocationRequestDTO request);

    void deleteLocation(Long id);
}