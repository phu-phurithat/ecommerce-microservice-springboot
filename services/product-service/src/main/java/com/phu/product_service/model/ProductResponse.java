package com.phu.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
