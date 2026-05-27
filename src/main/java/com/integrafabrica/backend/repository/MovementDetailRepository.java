package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.MovementDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDetailRepository extends JpaRepository<MovementDetail, Long> {

    // Recuperar todos los artículos que pertenecen a una misma transacción de
    // movimiento
    List<MovementDetail> findByMovementId(Long movementId);
}