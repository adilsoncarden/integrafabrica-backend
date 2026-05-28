package com.integrafabrica.backend.module.location.dto;

import java.time.LocalDateTime;

public class LocationResponseDTO {

    private Long id;
    private String aisle;
    private String rack;
    private String level;
    private String description;
    private LocalDateTime createdAt;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(Long id, String aisle, String rack, String level, String description,
            LocalDateTime createdAt) {
        this.id = id;
        this.aisle = aisle;
        this.rack = rack;
        this.level = level;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}