package com.integrafabrica.backend.module.supplier.service;

import java.util.List;

import com.integrafabrica.backend.module.supplier.dto.SupplierRequestDTO;
import com.integrafabrica.backend.module.supplier.dto.SupplierResponseDTO;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierRequestDTO request);

    List<SupplierResponseDTO> getAllSuppliers();

    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO request);

    void deleteSupplier(Long id);
}