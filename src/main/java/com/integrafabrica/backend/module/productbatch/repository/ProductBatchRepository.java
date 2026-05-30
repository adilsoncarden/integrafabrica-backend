package com.integrafabrica.backend.module.productbatch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.productbatch.model.ProductBatch;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    List<ProductBatch> findAllByOrderByIdAsc();

    List<ProductBatch> findByProductIdOrderByIdAsc(Long productId);

    Optional<ProductBatch> findByBatchCode(String batchCode);

    boolean existsByBatchCode(String batchCode);
}