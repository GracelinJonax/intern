package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.BusDetails;
import lombok.Data;

import java.util.List;

@Data
public class LayoutDto {
    private int[][][] layout;
    private List<BusDetails.Seat> seat;
}
