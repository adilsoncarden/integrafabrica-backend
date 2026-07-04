package com.integrafabrica.backend.module.productbatch.service;

import java.util.List;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchRequestDTO;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductBatchService {
    ProductBatchResponseDTO createBatch(ProductBatchRequestDTO request);

    Page<ProductBatchResponseDTO> getAllBatches(Pageable pageable);

    List<ProductBatchResponseDTO> getBatchesByProductId(Long productId);

    ProductBatchResponseDTO getBatchById(Long id);

    ProductBatchResponseDTO updateBatch(Long id, ProductBatchRequestDTO request);

    void deleteBatch(Long id);
}