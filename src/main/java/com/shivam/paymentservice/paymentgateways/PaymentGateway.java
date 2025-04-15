package com.shivam.paymentservice.paymentgateways;

public interface PaymentGateway {
    String createPaymentLink(Long amount,
                             Long orderId,
                             String phoneNumber,
                             String name,
                             String email);
}
