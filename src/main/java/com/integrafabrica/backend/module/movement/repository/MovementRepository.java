package com.integrafabrica.backend.module.movement.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.movement.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @EntityGraph(attributePaths = {"supplier", "performedBy", "performedBy.role"})
    List<Movement> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = {"supplier", "performedBy", "performedBy.role"})
    List<Movement> findByMovementTypeOrderByIdDesc(String movementType);

    @Override
    @EntityGraph(attributePaths = {"supplier", "performedBy", "performedBy.role"})
    Optional<Movement> findById(Long id);
}
