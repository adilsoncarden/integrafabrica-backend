package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.SupplierRequestDTO;
import com.integrafabrica.backend.dto.SupplierResponseDTO;
import java.util.List;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierRequestDTO request);

    List<SupplierResponseDTO> getAllSuppliers();

    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO request);

    void deleteSupplier(Long id);
}