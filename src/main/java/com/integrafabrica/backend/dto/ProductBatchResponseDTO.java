package com.integrafabrica.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductBatchResponseDTO {

    private Long id;

    @JsonProperty("product")
    private ProductResponseDTO product; // Retorna los datos limpios y completos del producto anidado

    @JsonProperty("batch_code")
    private String batchCode;

    @JsonProperty("expiration_date")
    private LocalDate expirationDate;

    @JsonProperty("initial_quantity")
    private Integer initialQuantity;

    @JsonProperty("current_quantity")
    private Integer currentQuantity;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public ProductBatchResponseDTO() {
    }

    public ProductBatchResponseDTO(Long id, ProductResponseDTO product, String batchCode,
            LocalDate expirationDate, Integer initialQuantity,
            Integer currentQuantity, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.batchCode = batchCode;
        this.expirationDate = expirationDate;
        this.initialQuantity = initialQuantity;
        this.currentQuantity = currentQuantity;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}