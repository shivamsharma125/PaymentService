package com.shivam.paymentservice.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentGatewayChooserStrategy(RazorpayPaymentGateway razorpayPaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
            }

    public PaymentGateway getBestPaymentGateway(){
        return razorpayPaymentGateway;
    }
}
