package com.integrafabrica.backend.module.dashboard.dto;

import com.integrafabrica.backend.module.movement.dto.MovementResponseDTO;
import com.integrafabrica.backend.module.product.dto.ProductResponseDTO;
import com.integrafabrica.backend.module.productbatch.dto.ProductBatchResponseDTO;
import java.math.BigDecimal;
import java.util.List;

public class DashboardSummaryDTO {

    private BigDecimal totalStock;
    private long productCount;
    private long batchCount;
    private long movementCount;
    private long categoryCount;
    private List<ProductResponseDTO> lowStockProducts;
    private List<ProductBatchResponseDTO> expiringBatches;
    private List<MovementResponseDTO> recentMovements;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(
            BigDecimal totalStock,
            long productCount,
            long batchCount,
            long movementCount,
            long categoryCount,
            List<ProductResponseDTO> lowStockProducts,
            List<ProductBatchResponseDTO> expiringBatches,
            List<MovementResponseDTO> recentMovements) {
        this.totalStock = totalStock;
        this.productCount = productCount;
        this.batchCount = batchCount;
        this.movementCount = movementCount;
        this.categoryCount = categoryCount;
        this.lowStockProducts = lowStockProducts;
        this.expiringBatches = expiringBatches;
        this.recentMovements = recentMovements;
    }

    public BigDecimal getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(BigDecimal totalStock) {
        this.totalStock = totalStock;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public long getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(long batchCount) {
        this.batchCount = batchCount;
    }

    public long getMovementCount() {
        return movementCount;
    }

    public void setMovementCount(long movementCount) {
        this.movementCount = movementCount;
    }

    public long getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(long categoryCount) {
        this.categoryCount = categoryCount;
    }

    public List<ProductResponseDTO> getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(List<ProductResponseDTO> lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public List<ProductBatchResponseDTO> getExpiringBatches() {
        return expiringBatches;
    }

    public void setExpiringBatches(List<ProductBatchResponseDTO> expiringBatches) {
        this.expiringBatches = expiringBatches;
    }

    public List<MovementResponseDTO> getRecentMovements() {
        return recentMovements;
    }

    public void setRecentMovements(List<MovementResponseDTO> recentMovements) {
        this.recentMovements = recentMovements;
    }
}
