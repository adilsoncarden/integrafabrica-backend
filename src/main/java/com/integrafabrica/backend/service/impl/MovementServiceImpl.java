package com.integrafabrica.backend.service.impl;

import com.integrafabrica.backend.dto.MovementRequestDTO;
import com.integrafabrica.backend.dto.MovementResponseDTO;
import com.integrafabrica.backend.dto.SupplierResponseDTO;
import com.integrafabrica.backend.model.Movement;
import com.integrafabrica.backend.model.Supplier;
import com.integrafabrica.backend.model.User;
import com.integrafabrica.backend.exception.ResourceNotFoundException;
import com.integrafabrica.backend.repository.MovementRepository;
import com.integrafabrica.backend.repository.SupplierRepository;
import com.integrafabrica.backend.repository.UserRepository;
import com.integrafabrica.backend.service.MovementService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    public MovementServiceImpl(MovementRepository movementRepository,
            SupplierRepository supplierRepository, UserRepository userRepository) {
        this.movementRepository = movementRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public MovementResponseDTO createMovement(MovementRequestDTO request) {
        User user = userRepository.findById(request.getPerformedBy())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario ejecutor no encontrado con ID: " + request.getPerformedBy()));

        Supplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Proveedor no encontrado con ID: " + request.getSupplierId()));
        } else if ("ENTRADA".equals(request.getMovementType())) {
            throw new IllegalArgumentException(
                    "Los movimientos de tipo ENTRADA requieren obligatoriamente un proveedor.");
        }

        Movement movement = new Movement(
                request.getMovementType().toLowerCase(), // Convertir a minúsculas para consistencia
                request.getReason(),
                supplier,
                request.getReferenceDocumentType(),
                request.getReferenceDocumentNumber(),
                user);

        Movement savedMovement = movementRepository.save(movement);
        return mapToResponseDTO(savedMovement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementResponseDTO> getAllMovements() {
        return movementRepository.findAllByOrderByIdDesc().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementResponseDTO> getMovementsByType(String type) {
        return movementRepository.findByMovementTypeOrderByIdDesc(type).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovementResponseDTO getMovementById(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con el ID: " + id));
        return mapToResponseDTO(movement);
    }

    @Override
    @Transactional
    public MovementResponseDTO updateMovement(Long id, MovementRequestDTO request) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con el ID: " + id));

        User user = userRepository.findById(request.getPerformedBy())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario ejecutor no encontrado con ID: " + request.getPerformedBy()));

        Supplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Proveedor no encontrado con ID: " + request.getSupplierId()));
        }

        movement.setMovementType(request.getMovementType().toLowerCase());
        movement.setReason(request.getReason());
        movement.setSupplier(supplier);
        movement.setReferenceDocumentType(request.getReferenceDocumentType());
        movement.setReferenceDocumentNumber(request.getReferenceDocumentNumber());
        movement.setPerformedBy(user);

        Movement updatedMovement = movementRepository.save(movement);
        return mapToResponseDTO(updatedMovement);
    }

    @Override
    @Transactional
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con el ID: " + id));
        movementRepository.delete(movement);
    }

    private MovementResponseDTO mapToResponseDTO(Movement movement) {
        SupplierResponseDTO supplierDTO = null;
        if (movement.getSupplier() != null) {
            Supplier s = movement.getSupplier();
            supplierDTO = new SupplierResponseDTO(
                    s.getId(), s.getRuc(), s.getCompanyName(), s.getContactName(),
                    s.getPhone(), s.getEmail(), s.getDeliveryTimeDays(), s.getCreatedAt());
        }

        return new MovementResponseDTO(
                movement.getId(),
                movement.getMovementType(),
                movement.getReason(),
                supplierDTO,
                movement.getReferenceDocumentType(),
                movement.getReferenceDocumentNumber(),
                movement.getPerformedBy().getUsername(), // Se retorna el username del ejecutor
                movement.getCreatedAt());
    }
}