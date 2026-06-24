package com.integrafabrica.backend.module.product.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT DISTINCT p FROM Product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            ORDER BY p.id ASC
            """)
    List<Product> findAllByOrderByIdAsc();

    @Query("""
            SELECT DISTINCT p FROM Product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            WHERE p.id = :id
            """)
    Optional<Product> findById(@Param("id") Long id);

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);
}
