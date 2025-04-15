package com.shivam.paymentservice.services;

import com.shivam.paymentservice.paymentgateways.PaymentGateway;
import com.shivam.paymentservice.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public PaymentServiceImpl(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy){
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }

    @Override
    public String createPaymentLink(Long amount,
                                    Long orderId,
                                    String phoneNumber,
                                    String name,
                                    String email) {

        PaymentGateway paymentGateway = paymentGatewayChooserStrategy.getBestPaymentGateway();

        return paymentGateway.createPaymentLink(
                amount,
                orderId,
                phoneNumber,
                name,
                email
        );
    }
}
