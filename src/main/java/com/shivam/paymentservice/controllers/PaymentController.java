package com.shivam.paymentservice.controllers;

import com.shivam.paymentservice.dtos.CreatePaymentRequestDto;
import com.shivam.paymentservice.dtos.CreatePaymentResponseDto;
import com.shivam.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    CreatePaymentResponseDto createPaymentLink(@RequestBody CreatePaymentRequestDto createPaymentRequestDto){
        String paymentLink = paymentService.createPaymentLink(createPaymentRequestDto.getOrderId());
        CreatePaymentResponseDto responseDto = new CreatePaymentResponseDto();
        responseDto.setPaymentLink(paymentLink);
        return responseDto;
    }
}
