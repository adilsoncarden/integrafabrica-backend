package com.integrafabrica.backend.module.productbatch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.productbatch.model.ProductBatch;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    @Query("""
            SELECT DISTINCT b FROM ProductBatch b
            JOIN FETCH b.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            ORDER BY b.id ASC
            """)
    List<ProductBatch> findAllByOrderByIdAsc();

    @Query("""
            SELECT DISTINCT b FROM ProductBatch b
            JOIN FETCH b.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE p.id = :productId
            ORDER BY b.id ASC
            """)
    List<ProductBatch> findByProductIdOrderByIdAsc(@Param("productId") Long productId);

    @Query("""
            SELECT DISTINCT b FROM ProductBatch b
            JOIN FETCH b.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE b.id = :id
            """)
    Optional<ProductBatch> findById(@Param("id") Long id);

    Optional<ProductBatch> findByBatchCode(String batchCode);

    boolean existsByBatchCode(String batchCode);
}
