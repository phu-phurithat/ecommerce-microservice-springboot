package com.phu.order_service.order;

import com.phu.order_service.customer.CustomerClient;
import com.phu.order_service.customer.CustomerResponse;
import com.phu.order_service.exception.BusinessException;
import com.phu.order_service.kafka.OrderConfirmation;
import com.phu.order_service.kafka.OrderProducer;
import com.phu.order_service.orderLine.OrderLineRequest;
import com.phu.order_service.orderLine.OrderLineService;
import com.phu.order_service.payment.PaymentClient;
import com.phu.order_service.payment.PaymentRequest;
import com.phu.order_service.product.ProductClient;
import com.phu.order_service.product.PurchaseRequest;
import com.phu.order_service.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public String createOrder(OrderRequest request) {
        // check customer --> OpenFeign
        CustomerResponse customer = customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: No customer exists with id " + request.getCustomerId()));

        // purchase products --> product-ms (RestTemplate)
        List<PurchaseResponse> purchasedProducts = callProductService(request.getProducts());

        // persist order
        Order order = orderRepository.save(orderMapper.toOrder(request));

        // persist order-line
        for (PurchaseRequest purchaseRequest : request.getProducts()) {
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .orderId(order.getId().toString())
                            .productId(purchaseRequest.getProductId())
                            .quantity(purchaseRequest.getQuantity())
                            .build()
            );

        }

        // start payment process
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .orderId(order.getId().toString())
                .orderReference(order.getReference())
                .customer(customer)
                .build();
        paymentClient.requestOrderPayment(paymentRequest);

        // send order confirmation --> notification-ms(kafka)
        orderProducer.sendOrderConfirmation(OrderConfirmation.builder()
                .orderReference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .customer(customer)
                .products(purchasedProducts)
                .build());

        return order.getId().toString();
    }

    private List<PurchaseResponse> callProductService(List<PurchaseRequest> products) {
        return productClient.purchaseProduct(products);
    }


    public List<OrderRespone> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderRespone)
                .toList();
    }

    public OrderRespone findById(String id) {
        return orderRepository.findById(Long.parseLong(id))
                .map(orderMapper::toOrderRespone)
                .orElseThrow(() -> new EntityNotFoundException("No order found with provided id:: " + id));
    }
}
