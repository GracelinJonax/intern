package com.example.geocoding.Dto;

import lombok.Data;

@Data
public class DistanceDto {
    private double latitude;
    private double longitude;
    private int distance;
    private long companyId;
}
