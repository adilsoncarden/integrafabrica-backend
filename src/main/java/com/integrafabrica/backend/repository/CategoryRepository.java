package com.integrafabrica.backend.repository;

import com.integrafabrica.backend.model.Category;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByIdAsc();

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}