package com.phu.payment_service.payment;

import com.phu.payment_service.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String orderId;
    private String orderReference;
    private Customer customer;
}
