package com.integrafabrica.backend.module.movement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.movement.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAllByOrderByIdDesc(); // Historial del más reciente al más antiguo

    List<Movement> findByMovementTypeOrderByIdDesc(String movementType);
}