package com.integrafabrica.backend.module.movementdetail.service.impl;

import com.integrafabrica.backend.module.movement.model.Movement;
import com.integrafabrica.backend.module.movement.repository.MovementRepository;
import com.integrafabrica.backend.module.movementdetail.dto.MovementDetailRequestDTO;
import com.integrafabrica.backend.module.movementdetail.dto.MovementDetailResponseDTO;
import com.integrafabrica.backend.module.movementdetail.model.MovementDetail;
import com.integrafabrica.backend.module.movementdetail.repository.MovementDetailRepository;
import com.integrafabrica.backend.module.movementdetail.service.MovementDetailService;
import com.integrafabrica.backend.module.product.model.Product;
import com.integrafabrica.backend.module.product.repository.ProductRepository;
import com.integrafabrica.backend.module.productbatch.model.ProductBatch;
import com.integrafabrica.backend.module.productbatch.repository.ProductBatchRepository;
import com.integrafabrica.backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovementDetailServiceImpl implements MovementDetailService {

    private final MovementDetailRepository movementDetailRepository;
    private final MovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final ProductBatchRepository productBatchRepository;

    public MovementDetailServiceImpl(MovementDetailRepository movementDetailRepository,
            MovementRepository movementRepository,
            ProductRepository productRepository,
            ProductBatchRepository productBatchRepository) {
        this.movementDetailRepository = movementDetailRepository;
        this.movementRepository = movementRepository;
        this.productRepository = productRepository;
        this.productBatchRepository = productBatchRepository;
    }

    @Override
    @Transactional
    public MovementDetailResponseDTO addDetail(MovementDetailRequestDTO request) {
        Movement movement = movementRepository.findById(request.getMovementId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cabecera de movimiento no encontrada con ID: " + request.getMovementId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + request.getProductId()));

        ProductBatch batch = null;
        if (request.getBatchId() != null) {
            batch = productBatchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Lote de producto no encontrado con ID: " + request.getBatchId()));
        }

        MovementDetail detail = new MovementDetail(movement, product, batch, request.getQuantity());
        MovementDetail savedDetail = movementDetailRepository.save(detail);

        return mapToResponseDTO(savedDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDetailResponseDTO> getDetailsByMovementId(Long movementId) {
        return movementDetailRepository.findByMovementId(movementId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovementDetailResponseDTO getDetailById(Long id) {
        MovementDetail detail = movementDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de movimiento no encontrado con ID: " + id));
        return mapToResponseDTO(detail);
    }

    @Override
    @Transactional
    public MovementDetailResponseDTO updateDetail(Long id, MovementDetailRequestDTO request) {
        MovementDetail detail = movementDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de movimiento no encontrado con ID: " + id));

        Movement movement = movementRepository.findById(request.getMovementId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cabecera de movimiento no encontrada con ID: " + request.getMovementId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + request.getProductId()));

        ProductBatch batch = null;
        if (request.getBatchId() != null) {
            batch = productBatchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Lote de producto no encontrado con ID: " + request.getBatchId()));
        }

        detail.setMovement(movement);
        detail.setProduct(product);
        detail.setProductBatch(batch);
        detail.setQuantity(request.getQuantity());

        MovementDetail updatedDetail = movementDetailRepository.save(detail);
        return mapToResponseDTO(updatedDetail);
    }

    @Override
    @Transactional
    public void deleteDetail(Long id) {
        MovementDetail detail = movementDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de movimiento no encontrado con ID: " + id));
        movementDetailRepository.delete(detail);
    }

    private MovementDetailResponseDTO mapToResponseDTO(MovementDetail detail) {
        Long batchId = (detail.getProductBatch() != null) ? detail.getProductBatch().getId() : null;
        String batchCode = (detail.getProductBatch() != null) ? detail.getProductBatch().getBatchCode() : "SIN_LOTE";

        return new MovementDetailResponseDTO(
                detail.getId(),
                detail.getMovement().getId(),
                detail.getProduct().getId(),
                detail.getProduct().getName(),
                batchId,
                batchCode,
                detail.getQuantity());
    }
}