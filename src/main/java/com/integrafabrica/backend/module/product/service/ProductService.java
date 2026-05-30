package com.integrafabrica.backend.module.product.service;

import java.util.List;

import com.integrafabrica.backend.module.product.dto.ProductRequestDTO;
import com.integrafabrica.backend.module.product.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO request);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);

    void deleteProduct(Long id);
}