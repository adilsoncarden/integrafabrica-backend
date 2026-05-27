package com.integrafabrica.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MovementRequestDTO {

    @NotBlank(message = "El tipo de movimiento es obligatorio.")
    @Pattern(regexp = "^(ENTRADA|SALIDA|MERMA)$", message = "El tipo de movimiento debe ser ENTRADA, SALIDA o MERMA.")
    @JsonProperty("movement_type")
    private String movementType;

    @NotBlank(message = "El motivo (reason) es obligatorio.")
    private String reason;

    @JsonProperty("supplier_id")
    private Long supplierId; // Puede ser null si es SALIDA o MERMA

    @JsonProperty("reference_document_type")
    private String referenceDocumentType;

    @JsonProperty("reference_document_number")
    private String referenceDocumentNumber;

    @NotNull(message = "El ID del usuario ejecutor (performed_by) es obligatorio.")
    @JsonProperty("performed_by")
    private Long performedBy;

    // Getters y Setters
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public Long getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Long performedBy) {
        this.performedBy = performedBy;
    }
}