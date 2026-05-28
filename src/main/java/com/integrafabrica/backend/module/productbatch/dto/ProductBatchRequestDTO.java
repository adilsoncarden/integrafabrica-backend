package com.integrafabrica.backend.module.productbatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class ProductBatchRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio.")
    @JsonProperty("product_id")
    private Long productId;

    @NotBlank(message = "El código de lote (batch_code) es obligatorio.")
    @Size(max = 100, message = "El código de lote no puede superar los 100 caracteres.")
    @JsonProperty("batch_code")
    private String batchCode;

    @NotNull(message = "La fecha de vencimiento (expiration_date) es obligatoria.")
    @FutureOrPresent(message = "La fecha de vencimiento no puede ser una fecha pasada.")
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;

    @NotNull(message = "La cantidad inicial (initial_quantity) es obligatoria.")
    @Min(value = 0, message = "La cantidad inicial no puede ser negativa.")
    @JsonProperty("initial_quantity")
    private Integer initialQuantity;

    @NotNull(message = "La cantidad actual (current_quantity) es obligatoria.")
    @Min(value = 0, message = "La cantidad actual no puede ser negativa.")
    @JsonProperty("current_quantity")
    private Integer currentQuantity;

    // Getters y Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}