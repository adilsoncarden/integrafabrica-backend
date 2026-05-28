package com.integrafabrica.backend.module.location.service.impl;

import com.integrafabrica.backend.module.location.dto.LocationRequestDTO;
import com.integrafabrica.backend.module.location.dto.LocationResponseDTO;
import com.integrafabrica.backend.module.location.model.Location;
import com.integrafabrica.backend.module.location.repository.LocationRepository;
import com.integrafabrica.backend.module.location.service.LocationService;
import com.integrafabrica.backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public LocationResponseDTO createLocation(LocationRequestDTO request) {
        if (locationRepository.existsByAisleAndRackAndLevel(request.getAisle(), request.getRack(),
                request.getLevel())) {
            throw new IllegalArgumentException(
                    String.format("Ya existe una ubicación registrada en el Pasillo: %s, Estante: %s, Nivel: %s",
                            request.getAisle(), request.getRack(), request.getLevel()));
        }
        Location location = new Location(request.getAisle(), request.getRack(), request.getLevel(),
                request.getDescription());
        Location savedLocation = locationRepository.save(location);
        return mapToResponseDTO(savedLocation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationResponseDTO> getAllLocations() {
        return locationRepository.findAllByOrderByIdAsc().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LocationResponseDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con el ID: " + id));
        return mapToResponseDTO(location);
    }

    @Override
    @Transactional
    public LocationResponseDTO updateLocation(Long id, LocationRequestDTO request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con el ID: " + id));

        locationRepository.findByAisleAndRackAndLevel(request.getAisle(), request.getRack(), request.getLevel())
                .ifPresent(existingLocation -> {
                    if (!existingLocation.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "La combinación de Pasillo, Estante y Nivel ya está asignada a otra ubicación.");
                    }
                });

        location.setAisle(request.getAisle());
        location.setRack(request.getRack());
        location.setLevel(request.getLevel());
        location.setDescription(request.getDescription());

        Location updatedLocation = locationRepository.save(location);
        return mapToResponseDTO(updatedLocation);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con el ID: " + id));
        locationRepository.delete(location);
    }

    private LocationResponseDTO mapToResponseDTO(Location location) {
        return new LocationResponseDTO(
                location.getId(),
                location.getAisle(),
                location.getRack(),
                location.getLevel(),
                location.getDescription(),
                location.getCreatedAt());
    }
}