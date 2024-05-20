package com.example.bookingservice.dto;

import com.example.bookingservice.model.Payment;
import lombok.Data;

import java.util.List;

@Data
public class TicketDto {
    private Payment payment;
    private String busName;
    private List<Integer> seats;
}
