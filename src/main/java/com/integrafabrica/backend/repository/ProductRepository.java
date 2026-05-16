package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdAsc();

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);
}