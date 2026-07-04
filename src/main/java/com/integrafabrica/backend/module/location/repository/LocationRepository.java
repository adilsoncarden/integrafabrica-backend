package com.integrafabrica.backend.module.location.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.integrafabrica.backend.module.location.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Location> findByAisleAndRackAndLevel(String aisle, String rack, String level);

    boolean existsByAisleAndRackAndLevel(String aisle, String rack, String level);
}