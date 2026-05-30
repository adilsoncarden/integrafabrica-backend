package com.integrafabrica.backend.module.productbatch.service.impl;

import com.integrafabrica.backend.module.category.dto.CategoryResponseDTO;
import com.integrafabrica.backend.module.location.dto.LocationResponseDTO;
import com.integrafabrica.backend.module.product.dto.ProductResponseDTO;
import com.integrafabrica.backend.module.product.model.Product;
import com.integrafabrica.backend.module.product.repository.ProductRepository;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchRequestDTO;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;
import com.integrafabrica.backend.module.productbatch.model.ProductBatch;
import com.integrafabrica.backend.module.productbatch.repository.ProductBatchRepository;
import com.integrafabrica.backend.module.productbatch.service.ProductBatchService;
import com.integrafabrica.backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductBatchServiceImpl implements ProductBatchService {

    private final ProductBatchRepository productBatchRepository;
    private final ProductRepository productRepository;

    public ProductBatchServiceImpl(ProductBatchRepository productBatchRepository, ProductRepository productRepository) {
        this.productBatchRepository = productBatchRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductBatchResponseDTO createBatch(ProductBatchRequestDTO request) {
        if (productBatchRepository.existsByBatchCode(request.getBatchCode())) {
            throw new IllegalArgumentException("Ya existe un lote registrado con el código: " + request.getBatchCode());
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + request.getProductId()));

        if (request.getCurrentQuantity() > request.getInitialQuantity()) {
            throw new IllegalArgumentException(
                    "La cantidad actual no puede ser mayor que la cantidad inicial del lote.");
        }

        ProductBatch batch = new ProductBatch(
                product,
                request.getBatchCode(),
                request.getExpirationDate(),
                request.getInitialQuantity(),
                request.getCurrentQuantity());

        ProductBatch savedBatch = productBatchRepository.save(batch);
        return mapToResponseDTO(savedBatch);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductBatchResponseDTO> getAllBatches() {
        return productBatchRepository.findAllByOrderByIdAsc().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductBatchResponseDTO> getBatchesByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + productId);
        }
        return productBatchRepository.findByProductIdOrderByIdAsc(productId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductBatchResponseDTO getBatchById(Long id) {
        ProductBatch batch = productBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con el ID: " + id));
        return mapToResponseDTO(batch);
    }

    @Override
    @Transactional
    public ProductBatchResponseDTO updateBatch(Long id, ProductBatchRequestDTO request) {
        ProductBatch batch = productBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con el ID: " + id));

        productBatchRepository.findByBatchCode(request.getBatchCode())
                .ifPresent(existingBatch -> {
                    if (!existingBatch.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "El código de lote '" + request.getBatchCode() + "' ya está registrado por otro lote.");
                    }
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + request.getProductId()));

        if (request.getCurrentQuantity() > request.getInitialQuantity()) {
            throw new IllegalArgumentException("La cantidad actual no puede superar la cantidad inicial.");
        }

        batch.setProduct(product);
        batch.setBatchCode(request.getBatchCode());
        batch.setExpirationDate(request.getExpirationDate());
        batch.setInitialQuantity(request.getInitialQuantity());
        batch.setCurrentQuantity(request.getCurrentQuantity());

        ProductBatch updatedBatch = productBatchRepository.save(batch);
        return mapToResponseDTO(updatedBatch);
    }

    @Override
    @Transactional
    public void deleteBatch(Long id) {
        ProductBatch batch = productBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con el ID: " + id));
        productBatchRepository.delete(batch);
    }

    private ProductBatchResponseDTO mapToResponseDTO(ProductBatch batch) {
        Product prod = batch.getProduct();

        CategoryResponseDTO catDTO = new CategoryResponseDTO(
                prod.getCategory().getId(),
                prod.getCategory().getName(),
                prod.getCategory().getDescription(),
                prod.getCategory().getCreatedAt());

        LocationResponseDTO locDTO = new LocationResponseDTO(
                prod.getLocation().getId(),
                prod.getLocation().getAisle(),
                prod.getLocation().getRack(),
                prod.getLocation().getLevel(),
                prod.getLocation().getDescription(),
                prod.getLocation().getCreatedAt());

        ProductResponseDTO prodDTO = new ProductResponseDTO(
                prod.getId(),
                prod.getSku(),
                prod.getName(),
                catDTO,
                locDTO,
                prod.getUnit(),
                prod.getStock(),
                prod.getMinStock(),
                prod.getCreatedAt());

        return new ProductBatchResponseDTO(
                batch.getId(),
                prodDTO,
                batch.getBatchCode(),
                batch.getExpirationDate(),
                batch.getInitialQuantity(),
                batch.getCurrentQuantity(),
                batch.getCreatedAt());
    }
}