package com.integrafabrica.backend.module.supplier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {

    @NotBlank(message = "El RUC es obligatorio.")
    @Pattern(regexp = "^[0-9]{11}$", message = "El RUC debe constar de exactamente 11 dígitos numéricos.")
    private String ruc;

    @NotBlank(message = "La razón social (company_name) es obligatoria.")
    @Size(max = 150, message = "La razón social no puede superar los 150 caracteres.")
    @JsonProperty("company_name")
    private String companyName;

    @Size(max = 100, message = "El nombre del contacto no puede superar los 100 caracteres.")
    @JsonProperty("contact_name")
    private String contactName;

    private String phone;

    @Email(message = "El formato del correo electrónico no es válido.")
    @Size(max = 100, message = "El correo electrónico no puede superar los 100 caracteres.")
    private String email;

    @NotNull(message = "El tiempo de entrega estimado es obligatorio.")
    @Min(value = 0, message = "El tiempo de entrega no puede ser un número negativo.")
    @JsonProperty("delivery_time_days")
    private Integer deliveryTimeDays;

    // Getters y Setters
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
}