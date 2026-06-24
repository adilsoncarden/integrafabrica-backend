package com.integrafabrica.backend.module.dashboard.service.impl;

import com.integrafabrica.backend.module.category.repository.CategoryRepository;
import com.integrafabrica.backend.module.dashboard.dto.DashboardSummaryDTO;
import com.integrafabrica.backend.module.dashboard.service.DashboardService;
import com.integrafabrica.backend.module.movement.dto.MovementResponseDTO;
import com.integrafabrica.backend.module.movement.model.Movement;
import com.integrafabrica.backend.module.movement.repository.MovementRepository;
import com.integrafabrica.backend.module.product.dto.ProductResponseDTO;
import com.integrafabrica.backend.module.product.model.Product;
import com.integrafabrica.backend.module.product.repository.ProductRepository;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;
import com.integrafabrica.backend.module.productbatch.model.ProductBatch;
import com.integrafabrica.backend.module.productbatch.repository.ProductBatchRepository;
import com.integrafabrica.backend.module.category.dto.CategoryResponseDTO;
import com.integrafabrica.backend.module.location.dto.LocationResponseDTO;
import com.integrafabrica.backend.module.supplier.dto.SupplierResponseDTO;
import com.integrafabrica.backend.module.supplier.model.Supplier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardServiceImpl implements DashboardService {

    private static final int LOW_STOCK_LIMIT = 20;
    private static final int EXPIRING_BATCH_LIMIT = 20;
    private static final int RECENT_MOVEMENTS_LIMIT = 5;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductBatchRepository productBatchRepository;
    private final MovementRepository movementRepository;

    public DashboardServiceImpl(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            ProductBatchRepository productBatchRepository,
            MovementRepository movementRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productBatchRepository = productBatchRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardSummaryDTO getSummary() {
        BigDecimal totalStock = productRepository.sumTotalStock();
        long productCount = productRepository.count();
        long batchCount = productBatchRepository.count();
        long movementCount = movementRepository.count();
        long categoryCount = categoryRepository.count();

        List<ProductResponseDTO> lowStockProducts = productRepository
                .findLowStockProducts(PageRequest.of(0, LOW_STOCK_LIMIT))
                .stream()
                .map(this::mapProduct)
                .toList();

        LocalDate today = LocalDate.now();
        LocalDate in30Days = today.plusDays(30);
        List<ProductBatchResponseDTO> expiringBatches = productBatchRepository
                .findExpiringBetween(today, in30Days, PageRequest.of(0, EXPIRING_BATCH_LIMIT))
                .stream()
                .map(this::mapBatch)
                .toList();

        List<MovementResponseDTO> recentMovements = movementRepository
                .findAllWithRelations(
                        PageRequest.of(0, RECENT_MOVEMENTS_LIMIT, Sort.by(Sort.Direction.DESC, "id")))
                .getContent()
                .stream()
                .map(this::mapMovement)
                .toList();

        return new DashboardSummaryDTO(
                totalStock,
                productCount,
                batchCount,
                movementCount,
                categoryCount,
                lowStockProducts,
                expiringBatches,
                recentMovements);
    }

    private ProductResponseDTO mapProduct(Product product) {
        CategoryResponseDTO category = new CategoryResponseDTO(
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getCategory().getCreatedAt());

        LocationResponseDTO location = new LocationResponseDTO(
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
                category,
                location,
                product.getUnit(),
                product.getStock(),
                product.getMinStock(),
                product.getCreatedAt());
    }

    private ProductBatchResponseDTO mapBatch(ProductBatch batch) {
        return new ProductBatchResponseDTO(
                batch.getId(),
                mapProduct(batch.getProduct()),
                batch.getBatchCode(),
                batch.getExpirationDate(),
                batch.getInitialQuantity(),
                batch.getCurrentQuantity(),
                batch.getCreatedAt());
    }

    private MovementResponseDTO mapMovement(Movement movement) {
        SupplierResponseDTO supplierDTO = null;
        if (movement.getSupplier() != null) {
            Supplier supplier = movement.getSupplier();
            supplierDTO = new SupplierResponseDTO(
                    supplier.getId(),
                    supplier.getRuc(),
                    supplier.getCompanyName(),
                    supplier.getContactName(),
                    supplier.getPhone(),
                    supplier.getEmail(),
                    supplier.getDeliveryTimeDays(),
                    supplier.getCreatedAt());
        }

        return new MovementResponseDTO(
                movement.getId(),
                movement.getMovementType(),
                movement.getReason(),
                supplierDTO,
                movement.getReferenceDocumentType(),
                movement.getReferenceDocumentNumber(),
                movement.getPerformedBy().getUsername(),
                movement.getCreatedAt());
    }
}
