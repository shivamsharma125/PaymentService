package com.shivam.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razor.pay.key.id}")
    private String razorpayKeyId;
    @Value("${razor.pay.key.secret}")
    private String razorpayKeySecret;

    @Bean
    public RazorpayClient getRazorpayClient() {
        try {
            return new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        } catch (RazorpayException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
