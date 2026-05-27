package com.integrafabrica.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movement_details")
public class MovementDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movement_id", nullable = false)
    private Movement movement;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = true) // Opcional (0..1) según el modelo relacional
    private ProductBatch productBatch;

    @Column(nullable = false)
    private Integer quantity;

    public MovementDetail() {
    }

    public MovementDetail(Movement movement, Product product, ProductBatch productBatch, Integer quantity) {
        this.movement = movement;
        this.product = product;
        this.productBatch = productBatch;
        this.quantity = quantity;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductBatch getProductBatch() {
        return productBatch;
    }

    public void setProductBatch(ProductBatch productBatch) {
        this.productBatch = productBatch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}