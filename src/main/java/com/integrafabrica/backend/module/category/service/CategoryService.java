package com.integrafabrica.backend.module.category.service;

import java.util.List;

import com.integrafabrica.backend.module.category.dto.CategoryRequestDTO;
import com.integrafabrica.backend.module.category.dto.CategoryResponseDTO;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);

    void deleteCategory(Long id);
}
