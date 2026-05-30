package com.integrafabrica.backend.module.movementdetail.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrafabrica.backend.module.movementdetail.model.MovementDetail;

@Repository
public interface MovementDetailRepository extends JpaRepository<MovementDetail, Long> {

    // Recuperar todos los artículos que pertenecen a una misma transacción de
    // movimiento
    List<MovementDetail> findByMovementId(Long movementId);
}