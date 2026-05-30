package com.integrafabrica.backend.module.movement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.integrafabrica.backend.module.supplier.dto.SupplierResponseDTO;

import java.time.LocalDateTime;

public class MovementResponseDTO {

    private Long id;

    @JsonProperty("movement_type")
    private String movementType;

    private String reason;

    @JsonProperty("supplier")
    private SupplierResponseDTO supplier; // Retorna los detalles del proveedor o null si no aplica

    @JsonProperty("reference_document_type")
    private String referenceDocumentType;

    @JsonProperty("reference_document_number")
    private String referenceDocumentNumber;

    @JsonProperty("performed_by_user")
    private String performedByUser; // Nombre o username del usuario simplificado para la respuesta

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public MovementResponseDTO() {
    }

    public MovementResponseDTO(Long id, String movementType, String reason, SupplierResponseDTO supplier,
            String referenceDocumentType, String referenceDocumentNumber,
            String performedByUser, LocalDateTime createdAt) {
        this.id = id;
        this.movementType = movementType;
        this.reason = reason;
        this.supplier = supplier;
        this.referenceDocumentType = referenceDocumentType;
        this.referenceDocumentNumber = referenceDocumentNumber;
        this.performedByUser = performedByUser;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public SupplierResponseDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponseDTO supplier) {
        this.supplier = supplier;
    }

    public String getReferenceDocumentType() {
        return referenceDocumentType;
    }

    public void setReferenceDocumentType(String referenceDocumentType) {
        this.referenceDocumentType = referenceDocumentType;
    }

    public String getReferenceDocumentNumber() {
        return referenceDocumentNumber;
    }

    public void setReferenceDocumentNumber(String referenceDocumentNumber) {
        this.referenceDocumentNumber = referenceDocumentNumber;
    }

    public String getPerformedByUser() {
        return performedByUser;
    }

    public void setPerformedByUser(String performedByUser) {
        this.performedByUser = performedByUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}