package com.shivam.paymentservice.controllers;

import com.shivam.paymentservice.dtos.CreatePaymentRequestDto;
import com.shivam.paymentservice.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> createPaymentLink(@RequestBody CreatePaymentRequestDto requestDto){
        String paymentLink = paymentService.createPaymentLink(
                requestDto.getAmount(),
                requestDto.getOrderId(),
                requestDto.getPhoneNumber(),
                requestDto.getName(),
                requestDto.getEmail()
        );
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }
}
