package com.phu.order_service.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    List<PurchaseResponse> purchaseProduct(@RequestBody List<PurchaseRequest> requestBody);
}
