package com.shivam.paymentservice.paymentgateways;

public interface PaymentGateway {
    String createPaymentLink(Long orderId);
}
