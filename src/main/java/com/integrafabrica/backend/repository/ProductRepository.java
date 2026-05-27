package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = { "category", "location" })
    List<Product> findAllByOrderByIdAsc();

    @EntityGraph(attributePaths = { "category", "location" })
    Optional<Product> findById(Long id);

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);
}