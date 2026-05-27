package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Supplier;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByOrderByIdAsc();

    Optional<Supplier> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}