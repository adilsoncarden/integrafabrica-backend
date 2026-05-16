package com.integrafabrica.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocationRequestDTO {

    @NotBlank(message = "El pasillo (aisle) no puede estar vacío.")
    @Size(max = 50, message = "El pasillo no puede superar los 50 caracteres.")
    private String aisle;

    @NotBlank(message = "El estante (rack) no puede estar vacío.")
    @Size(max = 50, message = "El estante no puede superar los 50 caracteres.")
    private String rack;

    @NotBlank(message = "El nivel (level) no puede estar vacío.")
    @Size(max = 50, message = "El nivel no puede superar los 50 caracteres.")
    private String level;

    private String description;

    // Getters y Setters
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
}