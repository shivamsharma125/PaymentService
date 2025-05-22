package com.shivam.paymentservice.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
    private final RazorpayPaymentGateway razorpayPaymentGateway;
    private final StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayChooserStrategy(RazorpayPaymentGateway razorpayPaymentGateway,
                                         StripePaymentGateway stripePaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
            }

    public PaymentGateway getBestPaymentGateway(){
        return stripePaymentGateway;
    }
}
