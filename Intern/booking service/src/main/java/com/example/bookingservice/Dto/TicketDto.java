package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.Payment;
import lombok.Data;

import java.util.List;
@Data
public class TicketDto {
    private Payment payment;
    private String busName;
    private List<Integer> seats;
}
