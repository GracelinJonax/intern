package com.example.bookingservice.Dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String bookId;
    private String paymentType;
    private double amount;
}
