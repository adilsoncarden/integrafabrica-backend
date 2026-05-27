package com.integrafabrica.backend.service.impl;

import com.integrafabrica.backend.dto.SupplierRequestDTO;
import com.integrafabrica.backend.dto.SupplierResponseDTO;
import com.integrafabrica.backend.model.Supplier;
import com.integrafabrica.backend.exception.ResourceNotFoundException;
import com.integrafabrica.backend.repository.SupplierRepository;
import com.integrafabrica.backend.service.SupplierService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public SupplierResponseDTO createSupplier(SupplierRequestDTO request) {
        if (supplierRepository.existsByRuc(request.getRuc())) {
            throw new IllegalArgumentException("Ya existe un proveedor registrado con el RUC: " + request.getRuc());
        }

        Supplier supplier = new Supplier(
                request.getRuc(),
                request.getCompanyName(),
                request.getContactName(),
                request.getPhone(),
                request.getEmail(),
                request.getDeliveryTimeDays());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToResponseDTO(savedSupplier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAllByOrderByIdAsc().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
        return mapToResponseDTO(supplier);
    }

    @Override
    @Transactional
    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));

        supplierRepository.findByRuc(request.getRuc())
                .ifPresent(existingSupplier -> {
                    if (!existingSupplier.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "El RUC '" + request.getRuc() + "' ya está registrado por otro proveedor.");
                    }
                });

        supplier.setRuc(request.getRuc());
        supplier.setCompanyName(request.getCompanyName());
        supplier.setContactName(request.getContactName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setDeliveryTimeDays(request.getDeliveryTimeDays());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return mapToResponseDTO(updatedSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
        supplierRepository.delete(supplier);
    }

    private SupplierResponseDTO mapToResponseDTO(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getRuc(),
                supplier.getCompanyName(),
                supplier.getContactName(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getDeliveryTimeDays(),
                supplier.getCreatedAt());
    }
}