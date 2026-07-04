package com.integrafabrica.backend.module.category.service;

import com.integrafabrica.backend.module.category.dto.CategoryRequestDTO;
import com.integrafabrica.backend.module.category.dto.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    Page<CategoryResponseDTO> getAllCategories(Pageable pageable);

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);

    void deleteCategory(Long id);
}
