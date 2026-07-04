package com.integrafabrica.backend.module.movement.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.integrafabrica.backend.module.movement.model.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query(value = """
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            """, countQuery = "SELECT COUNT(DISTINCT m) FROM Movement m")
    Page<Movement> findAllWithRelations(Pageable pageable);

    @Query(value = """
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            WHERE m.movementType = :movementType
            """, countQuery = """
            SELECT COUNT(DISTINCT m) FROM Movement m
            WHERE m.movementType = :movementType
            """)
    Page<Movement> findByMovementTypeWithRelations(
            @Param("movementType") String movementType, Pageable pageable);

    @Query("""
            SELECT DISTINCT m FROM Movement m
            LEFT JOIN FETCH m.supplier
            JOIN FETCH m.performedBy u
            JOIN FETCH u.role
            WHERE m.id = :id
            """)
    Optional<Movement> findById(@Param("id") Long id);
}
