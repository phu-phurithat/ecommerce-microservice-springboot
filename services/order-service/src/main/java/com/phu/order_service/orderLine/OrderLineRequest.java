package com.phu.order_service.orderLine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineRequest {

    private String id;
    private String orderId;
    private String productId;
    private double quantity;
}
