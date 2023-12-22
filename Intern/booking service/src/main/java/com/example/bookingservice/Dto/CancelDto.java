package com.example.bookingservice.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CancelDto {
    private String pnr;
    private List<Integer> seats;
}
