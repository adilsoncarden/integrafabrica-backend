package com.integrafabrica.backend.service.impl;

import com.integrafabrica.backend.dto.CategoryResponseDTO;
import com.integrafabrica.backend.dto.LocationResponseDTO;
import com.integrafabrica.backend.dto.ProductRequestDTO;
import com.integrafabrica.backend.dto.ProductResponseDTO;
import com.integrafabrica.backend.model.Category;
import com.integrafabrica.backend.model.Location;
import com.integrafabrica.backend.model.Product;
import com.integrafabrica.backend.exception.ResourceNotFoundException;
import com.integrafabrica.backend.repository.CategoryRepository;
import com.integrafabrica.backend.repository.LocationRepository;
import com.integrafabrica.backend.repository.ProductRepository;
import com.integrafabrica.backend.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public ProductServiceImpl(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            LocationRepository locationRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        if (productRepository.existsBySku(request.getSku())) {
            throw new IllegalArgumentException("Ya existe un producto registrado con el SKU: " + request.getSku());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + request.getCategoryId()));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ubicación no encontrada con ID: " + request.getLocationId()));

        Product product = new Product(
                request.getSku(),
                request.getName(),
                category,
                location,
                request.getUnit(),
                request.getStock(),
                request.getMinStock());

        Product savedProduct = productRepository.save(product);
        return mapToResponseDTO(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
        return mapToResponseDTO(product);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));

        productRepository.findBySku(request.getSku())
                .ifPresent(existingProduct -> {
                    if (!existingProduct.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "El SKU '" + request.getSku() + "' ya está siendo usado por otro producto.");
                    }
                });

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + request.getCategoryId()));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ubicación no encontrada con ID: " + request.getLocationId()));

        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setCategory(category);
        product.setLocation(location);
        product.setUnit(request.getUnit());
        product.setStock(request.getStock());
        product.setMinStock(request.getMinStock());

        Product updatedProduct = productRepository.save(product);
        return mapToResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
        productRepository.delete(product);
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        CategoryResponseDTO catDTO = new CategoryResponseDTO(
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getCategory().getCreatedAt());

        LocationResponseDTO locDTO = new LocationResponseDTO(
                product.getLocation().getId(),
                product.getLocation().getAisle(),
                product.getLocation().getRack(),
                product.getLocation().getLevel(),
                product.getLocation().getDescription(),
                product.getLocation().getCreatedAt());

        return new ProductResponseDTO(
                product.getId(),
                product.getSku(),
                product.getName(),
                catDTO,
                locDTO,
                product.getUnit(),
                product.getStock(),
                product.getMinStock(),
                product.getCreatedAt());
    }
}