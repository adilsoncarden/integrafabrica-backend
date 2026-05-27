package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.ProductBatchRequestDTO;
import com.integrafabrica.backend.dto.ProductBatchResponseDTO;
import java.util.List;

public interface ProductBatchService {
    ProductBatchResponseDTO createBatch(ProductBatchRequestDTO request);

    List<ProductBatchResponseDTO> getAllBatches();

    List<ProductBatchResponseDTO> getBatchesByProductId(Long productId);

    ProductBatchResponseDTO getBatchById(Long id);

    ProductBatchResponseDTO updateBatch(Long id, ProductBatchRequestDTO request);

    void deleteBatch(Long id);
}