package com.phu.order_service.orderLine;

import com.phu.order_service.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .order(Order.builder()
                        .id(Long.parseLong(orderLineRequest.getOrderId()))
                        .build())
                .productId(orderLineRequest.getProductId())
                .quantity(orderLineRequest.getQuantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId().toString())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
