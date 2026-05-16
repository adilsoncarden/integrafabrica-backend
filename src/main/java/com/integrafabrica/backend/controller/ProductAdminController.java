package com.integrafabrica.backend.controller;

import com.integrafabrica.backend.dto.ProductRequestDTO;
import com.integrafabrica.backend.dto.ProductResponseDTO;
import com.integrafabrica.backend.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/productos")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}