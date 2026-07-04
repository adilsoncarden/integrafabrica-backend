package com.integrafabrica.backend.module.supplier.service;

import com.integrafabrica.backend.module.supplier.dto.SupplierRequestDTO;
import com.integrafabrica.backend.module.supplier.dto.SupplierResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierRequestDTO request);

    Page<SupplierResponseDTO> getAllSuppliers(Pageable pageable);

    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO request);

    void deleteSupplier(Long id);
}