package com.phu.product_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Long productId;
    @NotNull(message = "Quantity is mandatory")
    private double quantity;
}
