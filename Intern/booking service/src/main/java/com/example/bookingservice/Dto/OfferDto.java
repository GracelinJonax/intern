package com.example.bookingservice.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferDto {
    private long id;
    private String information;
    private String status;
    private boolean scratch;
    private LocalDate validDate;
}
