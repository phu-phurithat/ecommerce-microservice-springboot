package com.phu.product_service.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Product description is required")
    private String description;
    @Positive(message = "Available quantity must be greater than 0")
    private double availableQuantity;
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Category id is required")
    private Long categoryId;
}
