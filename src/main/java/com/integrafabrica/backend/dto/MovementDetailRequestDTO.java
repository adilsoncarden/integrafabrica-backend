package com.integrafabrica.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovementDetailRequestDTO {

    @NotNull(message = "El ID del movimiento (movement_id) es obligatorio.")
    @JsonProperty("movement_id")
    private Long movementId;

    @NotNull(message = "El ID del producto (product_id) es obligatorio.")
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("batch_id")
    private Long batchId; // Opcional según la naturaleza de la merma/salida

    @NotNull(message = "La cantidad (quantity) es obligatoria.")
    @Positive(message = "La cantidad debe ser un número entero mayor a cero.")
    private Integer quantity;

    // Getters y Setters
    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}