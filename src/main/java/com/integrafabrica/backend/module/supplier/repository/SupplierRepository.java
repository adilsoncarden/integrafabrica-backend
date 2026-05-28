package com.integrafabrica.backend.module.supplier.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.supplier.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByOrderByIdAsc();

    Optional<Supplier> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}