package com.example.bookingservice.dto;

import com.example.bookingservice.model.Passengers;
import lombok.Data;

import java.util.List;

@Data
public class BlockDto {
    private String busId;
    private String userId;
    private String mobileNo;
    private String email;
    private List<Passengers> passengersList;
    private GetLayoutDto.Journey journey;
}
