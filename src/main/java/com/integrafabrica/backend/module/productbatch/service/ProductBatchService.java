package com.integrafabrica.backend.module.productbatch.service;

import java.util.List;

import com.integrafabrica.backend.module.productbatch.dto.ProductBatchRequestDTO;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;

public interface ProductBatchService {
    ProductBatchResponseDTO createBatch(ProductBatchRequestDTO request);

    List<ProductBatchResponseDTO> getAllBatches();

    List<ProductBatchResponseDTO> getBatchesByProductId(Long productId);

    ProductBatchResponseDTO getBatchById(Long id);

    ProductBatchResponseDTO updateBatch(Long id, ProductBatchRequestDTO request);

    void deleteBatch(Long id);
}