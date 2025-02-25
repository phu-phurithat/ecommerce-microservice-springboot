package com.phu.order_service.payment;

import com.phu.order_service.customer.CustomerResponse;
import com.phu.order_service.order.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private String id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String orderId;
    private String orderReference;
    private CustomerResponse customer;
}
