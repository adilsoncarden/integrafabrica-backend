package com.integrafabrica.backend.module.category.service.impl;

import com.integrafabrica.backend.module.category.dto.CategoryRequestDTO;
import com.integrafabrica.backend.module.category.dto.CategoryResponseDTO;
import com.integrafabrica.backend.module.category.model.Category;
import com.integrafabrica.backend.module.category.repository.CategoryRepository;
import com.integrafabrica.backend.module.category.service.CategoryService;
import com.integrafabrica.backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + request.getName());
        }
        Category category = new Category(request.getName(), request.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return mapToResponseDTO(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAllByOrderByIdAsc().stream()
        // return categoryRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));
        return mapToResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));

        categoryRepository.findByName(request.getName())
                .ifPresent(existingCategory -> {
                    if (!existingCategory.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "El nombre de categoría '" + request.getName() + "' ya está en uso.");
                    }
                });

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return mapToResponseDTO(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));
        categoryRepository.delete(category);
    }

    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt());
    }
}