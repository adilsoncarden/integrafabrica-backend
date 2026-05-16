package com.integrafabrica.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "locations", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "aisle", "rack", "level" }, name = "uk_aisle_rack_level")
})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String aisle; // Pasillo

    @Column(nullable = false, length = 50)
    private String rack; // Estante

    @Column(nullable = false, length = 50)
    private String level; // Nivel

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Location() {
    }

    public Location(String aisle, String rack, String level, String description) {
        this.aisle = aisle;
        this.rack = rack;
        this.level = level;
        this.description = description;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}