package com.phu.payment_service.payment;

import com.phu.payment_service.notification.NotificationProducer;
import com.phu.payment_service.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public String createPayment(PaymentRequest request) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(request));

        notificationProducer.sendNotification(
                PaymentNotificationRequest.builder()
                        .orderReference(request.getOrderReference())
                        .amount(request.getAmount())
                        .paymentMethod(request.getPaymentMethod())
                        .customerFirstName(request.getCustomer().getFirstName())
                        .customerLastName(request.getCustomer().getLastName())
                        .customerEmail(request.getCustomer().getEmail())
                        .build());
        return payment.getId().toString();
    }

}
