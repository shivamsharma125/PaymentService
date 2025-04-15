package com.shivam.paymentservice.services;

public interface PaymentService {
    String createPaymentLink(Long amount,
                             Long orderId,
                             String phoneNumber,
                             String name,
                             String email);
}
