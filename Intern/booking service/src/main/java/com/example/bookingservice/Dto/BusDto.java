package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.BusDetails;
import lombok.Data;

@Data
public class BusDto {
    private BusDetails busDetails;
    private int [][][] layout;
}
