package com.integrafabrica.backend.module.movement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.movement.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("""
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            ORDER BY m.id DESC
            """)
    List<Movement> findAllByOrderByIdDesc();

    @Query("""
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            WHERE m.movementType = :movementType
            ORDER BY m.id DESC
            """)
    List<Movement> findByMovementTypeOrderByIdDesc(String movementType);
}
