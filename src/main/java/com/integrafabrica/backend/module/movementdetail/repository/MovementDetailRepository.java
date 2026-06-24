package com.integrafabrica.backend.module.movementdetail.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.movementdetail.model.MovementDetail;

@Repository
public interface MovementDetailRepository extends JpaRepository<MovementDetail, Long> {

    @Query("""
            SELECT DISTINCT d FROM MovementDetail d
            JOIN FETCH d.movement
            JOIN FETCH d.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            LEFT JOIN FETCH d.productBatch
            WHERE d.movement.id = :movementId
            """)
    List<MovementDetail> findByMovementId(@Param("movementId") Long movementId);

    @Query("""
            SELECT DISTINCT d FROM MovementDetail d
            JOIN FETCH d.movement
            JOIN FETCH d.product p
            JOIN FETCH p.category
            JOIN FETCH p.location
            LEFT JOIN FETCH d.productBatch
            WHERE d.id = :id
            """)
    Optional<MovementDetail> findById(@Param("id") Long id);

    @Query(
            value = """
                    SELECT DISTINCT d FROM MovementDetail d
                    JOIN FETCH d.movement
                    JOIN FETCH d.product p
                    JOIN FETCH p.category
                    JOIN FETCH p.location
                    LEFT JOIN FETCH d.productBatch
                    """,
            countQuery = "SELECT COUNT(DISTINCT d) FROM MovementDetail d")
    Page<MovementDetail> findAllWithRelations(Pageable pageable);
}
