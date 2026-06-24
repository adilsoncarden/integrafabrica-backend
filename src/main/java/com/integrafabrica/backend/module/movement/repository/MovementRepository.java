package com.integrafabrica.backend.module.movement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<Movement> findByMovementTypeOrderByIdDesc(@Param("movementType") String movementType);

    @Query("""
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            WHERE m.id = :id
            """)
    Optional<Movement> findById(@Param("id") Long id);
}
