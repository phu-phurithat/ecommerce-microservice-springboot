package com.phu.order_service.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(String orderId) {
        return orderLineRepository.findAllByOrderId(Long.parseLong(orderId))
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
