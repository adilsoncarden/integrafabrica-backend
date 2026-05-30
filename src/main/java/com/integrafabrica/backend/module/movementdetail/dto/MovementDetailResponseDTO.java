package com.integrafabrica.backend.module.movementdetail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovementDetailResponseDTO {

    private Long id;

    @JsonProperty("movement_id")
    private Long movementId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("batch_id")
    private Long batchId;

    @JsonProperty("batch_code")
    private String batchCode; // Atributo descriptivo del lote para la vista pública

    private Integer quantity;

    public MovementDetailResponseDTO() {
    }

    public MovementDetailResponseDTO(Long id, Long movementId, Long productId, String productName,
            Long batchId, String batchCode, Integer quantity) {
        this.id = id;
        this.movementId = movementId;
        this.productId = productId;
        this.productName = productName;
        this.batchId = batchId;
        this.batchCode = batchCode;
        this.quantity = quantity;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}