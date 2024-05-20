package com.example.bookingservice.dto;

import com.example.bookingservice.model.BusDetails;
import lombok.Data;

@Data
public class BusDto {
    private BusDetails busDetails;
    private int[][][] layout;
}
