package com.phu.order_service.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    String requestOrderPayment(@RequestBody PaymentRequest request);
}
