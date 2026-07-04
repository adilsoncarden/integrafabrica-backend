package com.integrafabrica.backend.module.product.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.integrafabrica.backend.module.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            SELECT DISTINCT p FROM Product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            """, countQuery = "SELECT COUNT(DISTINCT p) FROM Product p")
    Page<Product> findAllWithRelations(Pageable pageable);

    @Query("""
            SELECT DISTINCT p FROM Product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE p.id = :id
            """)
    Optional<Product> findById(@Param("id") Long id);

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    @Query("SELECT COALESCE(SUM(p.stock), 0) FROM Product p")
    java.math.BigDecimal sumTotalStock();

    @Query("""
            SELECT DISTINCT p FROM Product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE p.stock < p.minStock
            ORDER BY p.stock ASC
            """)
    java.util.List<Product> findLowStockProducts(org.springframework.data.domain.Pageable pageable);
}
