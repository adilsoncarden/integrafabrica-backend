package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.LocationRequestDTO;
import com.integrafabrica.backend.dto.LocationResponseDTO;
import java.util.List;

public interface LocationService {
    LocationResponseDTO createLocation(LocationRequestDTO request);

    List<LocationResponseDTO> getAllLocations();

    LocationResponseDTO getLocationById(Long id);

    LocationResponseDTO updateLocation(Long id, LocationRequestDTO request);

    void deleteLocation(Long id);
}