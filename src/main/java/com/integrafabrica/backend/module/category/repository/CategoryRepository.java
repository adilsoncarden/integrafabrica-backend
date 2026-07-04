package com.integrafabrica.backend.module.category.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.integrafabrica.backend.module.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}