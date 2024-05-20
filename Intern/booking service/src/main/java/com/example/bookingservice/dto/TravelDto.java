package com.example.bookingservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TravelDto {
    private String boardingPoint;
    private String endPoint;
    private Date travelDate;
}
