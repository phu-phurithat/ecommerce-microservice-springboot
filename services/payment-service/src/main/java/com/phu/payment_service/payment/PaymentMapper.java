package com.phu.payment_service.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .orderId(Long.parseLong(request.getOrderId()))
                .build();
    }
}
