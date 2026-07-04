package com.integrafabrica.backend.module.productbatch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.integrafabrica.backend.module.productbatch.model.ProductBatch;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    @Query(value = """
            SELECT DISTINCT b FROM ProductBatch b
            JOIN FETCH b.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            """, countQuery = "SELECT COUNT(DISTINCT b) FROM ProductBatch b")
    Page<ProductBatch> findAllWithRelations(Pageable pageable);

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

    @Query("""
            SELECT DISTINCT b FROM ProductBatch b
            JOIN FETCH b.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE b.expirationDate BETWEEN :startDate AND :endDate
            ORDER BY b.expirationDate ASC
            """)
    java.util.List<ProductBatch> findExpiringBetween(
            @Param("startDate") java.time.LocalDate startDate,
            @Param("endDate") java.time.LocalDate endDate,
            org.springframework.data.domain.Pageable pageable);
}
