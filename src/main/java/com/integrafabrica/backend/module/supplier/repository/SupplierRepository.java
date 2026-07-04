package com.integrafabrica.backend.module.supplier.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.integrafabrica.backend.module.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Supplier> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}