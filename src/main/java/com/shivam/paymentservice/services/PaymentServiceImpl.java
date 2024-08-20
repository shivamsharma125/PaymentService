package com.shivam.paymentservice.services;

import com.shivam.paymentservice.paymentgateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentServiceImpl(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String createPaymentLink(Long orderId) {
        return paymentGateway.createPaymentLink(orderId);
    }
}
