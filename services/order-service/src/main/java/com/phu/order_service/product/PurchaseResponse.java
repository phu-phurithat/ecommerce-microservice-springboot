package com.phu.order_service.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponse {

    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
