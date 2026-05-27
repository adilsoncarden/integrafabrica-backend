package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Movement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAllByOrderByIdDesc(); // Historial del más reciente al más antiguo

    List<Movement> findByMovementTypeOrderByIdDesc(String movementType);
}