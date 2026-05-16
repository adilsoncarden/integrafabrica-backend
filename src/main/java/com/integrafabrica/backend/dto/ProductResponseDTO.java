package com.integrafabrica.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponseDTO {

    private Long id;
    private String sku;
    private String name;
    private CategoryResponseDTO category; // Retornamos el DTO de categoría completo
    private LocationResponseDTO location; // Retornamos el DTO de ubicación completo
    private String unit;
    private BigDecimal stock;
    private BigDecimal minStock;
    private LocalDateTime createdAt;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String sku, String name, CategoryResponseDTO category,
            LocationResponseDTO location, String unit, BigDecimal stock,
            BigDecimal minStock, LocalDateTime createdAt) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.location = location;
        this.unit = unit;
        this.stock = stock;
        this.minStock = minStock;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public LocationResponseDTO getLocation() {
        return location;
    }

    public void setLocation(LocationResponseDTO location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getMinStock() {
        return minStock;
    }

    public void setMinStock(BigDecimal minStock) {
        this.minStock = minStock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}