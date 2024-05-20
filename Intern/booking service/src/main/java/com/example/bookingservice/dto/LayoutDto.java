package com.example.bookingservice.dto;

import com.example.bookingservice.model.BusDetails;
import lombok.Data;

import java.util.List;

@Data
public class LayoutDto {
    private int[][][] layout;
    private List<BusDetails.Seat> seat;
}
