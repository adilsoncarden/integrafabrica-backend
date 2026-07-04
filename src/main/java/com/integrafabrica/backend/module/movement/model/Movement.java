package com.integrafabrica.backend.module.movement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.integrafabrica.backend.module.auth.model.User;
import com.integrafabrica.backend.module.supplier.model.Supplier;

@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movement_type", nullable = false, length = 20)
    private String movementType; // ENTRADA, SALIDA, MERMA

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = true) // Es opcional según el diagrama (0..1)
    private Supplier supplier;

    @Column(name = "reference_document_type", length = 50)
    private String referenceDocumentType; // BOLETA, FACTURA, GUIA_REMISION, etc.

    @Column(name = "reference_document_number", length = 50)
    private String referenceDocumentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by", nullable = false)
    private User performedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Movement() {
    }

    public Movement(String movementType, String reason, Supplier supplier,
            String referenceDocumentType, String referenceDocumentNumber, User performedBy) {
        setMovementType(movementType); // Usamos el setter para estandarizar a mayúsculas
        this.reason = reason;
        this.supplier = supplier;
        setReferenceDocumentType(referenceDocumentType); // Usamos el setter para estandarizar a mayúsculas
        this.referenceDocumentNumber = referenceDocumentNumber;
        this.performedBy = performedBy;
    }

    /**
     * Ciclo de vida de JPA: Fuerza la conversión a mayúsculas y limpia espacios
     * en blanco antes de persistir o actualizar en la base de datos de Supabase.
     */
    @PrePersist
    @PreUpdate
    private void ensureConstraintsFormat() {
        if (this.movementType != null) {
            this.movementType = this.movementType.toUpperCase().trim();
        }
        if (this.referenceDocumentType != null) {
            this.referenceDocumentType = this.referenceDocumentType.toUpperCase().trim();
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = (movementType != null) ? movementType.toUpperCase().trim() : null;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getReferenceDocumentType() {
        return referenceDocumentType;
    }

    public void setReferenceDocumentType(String referenceDocumentType) {
        this.referenceDocumentType = (referenceDocumentType != null) ? referenceDocumentType.toUpperCase().trim()
                : null;
    }

    public String getReferenceDocumentNumber() {
        return referenceDocumentNumber;
    }

    public void setReferenceDocumentNumber(String referenceDocumentNumber) {
        this.referenceDocumentNumber = referenceDocumentNumber;
    }

    public User getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(User performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}