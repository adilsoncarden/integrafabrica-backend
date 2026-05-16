package com.integrafabrica.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductRequestDTO {

    @NotBlank(message = "El SKU es obligatorio.")
    @Size(max = 50, message = "El SKU no puede superar los 50 caracteres.")
    private String sku;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres.")
    private String name;

    @NotNull(message = "El ID de la categoría es obligatorio.")
    private Long categoryId;

    @NotNull(message = "El ID de la ubicación es obligatorio.")
    private Long locationId;

    @NotBlank(message = "La unidad de medida (unit) es obligatoria.")
    @Size(max = 20, message = "La unidad no puede superar los 20 caracteres.")
    private String unit;

    @NotNull(message = "El stock inicial no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El stock no puede ser negativo.")
    private BigDecimal stock;

    @NotNull(message = "El stock mínimo requerido no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El stock mínimo no puede ser negativo.")
    private BigDecimal minStock;

    // Getters y Setters
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
}