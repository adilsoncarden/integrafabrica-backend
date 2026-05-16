package com.integrafabrica.backend.service;

import com.integrafabrica.backend.dto.ProductRequestDTO;
import com.integrafabrica.backend.dto.ProductResponseDTO;
import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO request);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);

    void deleteProduct(Long id);
}