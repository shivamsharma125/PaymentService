package com.shivam.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentRequestDto {
    private Long amount;
    private Long orderId;
    private String phoneNumber;
    private String name;
    private String email;
}
