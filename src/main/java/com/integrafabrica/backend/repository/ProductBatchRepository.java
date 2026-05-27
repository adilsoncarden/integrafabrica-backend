package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.ProductBatch;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    List<ProductBatch> findAllByOrderByIdAsc();

    List<ProductBatch> findByProductIdOrderByIdAsc(Long productId);

    Optional<ProductBatch> findByBatchCode(String batchCode);

    boolean existsByBatchCode(String batchCode);
}