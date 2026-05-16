package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Location;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByOrderByIdAsc();

    Optional<Location> findByAisleAndRackAndLevel(String aisle, String rack, String level);

    boolean existsByAisleAndRackAndLevel(String aisle, String rack, String level);
}