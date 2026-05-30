package com.integrafabrica.backend.module.productbatch.controller;

import com.integrafabrica.backend.module.productbatch.dto.ProductBatchRequestDTO;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;
import com.integrafabrica.backend.module.productbatch.service.ProductBatchService;

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
@RequestMapping("/admin/lotes")
public class ProductBatchAdminController {

    private final ProductBatchService productBatchService;

    public ProductBatchAdminController(ProductBatchService productBatchService) {
        this.productBatchService = productBatchService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductBatchResponseDTO> createBatch(@Valid @RequestBody ProductBatchRequestDTO request) {
        ProductBatchResponseDTO response = productBatchService.createBatch(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductBatchResponseDTO>> getAllBatches() {
        return ResponseEntity.ok(productBatchService.getAllBatches());
    }

    @GetMapping("producto/{productId}")
    public ResponseEntity<List<ProductBatchResponseDTO>> getBatchesByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productBatchService.getBatchesByProductId(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBatchResponseDTO> getBatchById(@PathVariable Long id) {
        return ResponseEntity.ok(productBatchService.getBatchById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductBatchResponseDTO> updateBatch(
            @PathVariable Long id,
            @Valid @RequestBody ProductBatchRequestDTO request) {
        return ResponseEntity.ok(productBatchService.updateBatch(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        productBatchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
}