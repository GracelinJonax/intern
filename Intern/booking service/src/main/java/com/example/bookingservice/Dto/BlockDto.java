package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.Passengers;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlockDto {
    private String busId;
    private String userId;
    private String mobileNo;
    private String email;
    private List<Passengers> passengersList;
    private getLayoutDto.Journey journey;
}
