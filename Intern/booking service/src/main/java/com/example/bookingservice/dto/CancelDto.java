package com.example.bookingservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CancelDto {
    private String pnr;
    private List<Integer> seats;
}
