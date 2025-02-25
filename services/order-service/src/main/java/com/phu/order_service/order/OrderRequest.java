package com.phu.order_service.order;

import com.phu.order_service.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String id;
    private String reference;
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;
    @NotNull(message = "Payment method must be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer id must be present")
    @NotEmpty(message = "Customer id must be present")
    @NotBlank(message = "Customer id must be present")
    private String customerId;
    @NotEmpty(message = "You should have at least one product")
    private List<PurchaseRequest> products;
}
