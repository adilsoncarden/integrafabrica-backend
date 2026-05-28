package com.integrafabrica.backend.module.supplier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class SupplierResponseDTO {

    private Long id;
    private String ruc;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("contact_name")
    private String contactName;

    private String phone;
    private String email;

    @JsonProperty("delivery_time_days")
    private Integer deliveryTimeDays;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public SupplierResponseDTO() {
    }

    public SupplierResponseDTO(Long id, String ruc, String companyName, String contactName,
            String phone, String email, Integer deliveryTimeDays, LocalDateTime createdAt) {
        this.id = id;
        this.ruc = ruc;
        this.companyName = companyName;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.deliveryTimeDays = deliveryTimeDays;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDeliveryTimeDays() {
        return deliveryTimeDays;
    }

    public void setDeliveryTimeDays(Integer deliveryTimeDays) {
        this.deliveryTimeDays = deliveryTimeDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}