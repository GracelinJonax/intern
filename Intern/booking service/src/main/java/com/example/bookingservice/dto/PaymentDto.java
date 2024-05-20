package com.example.bookingservice.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String bookId;
    private String paymentType;
    private double amount;
}
