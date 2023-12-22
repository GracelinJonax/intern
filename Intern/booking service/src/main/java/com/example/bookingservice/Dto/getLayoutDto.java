package com.example.bookingservice.Dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class getLayoutDto {
    private String busId;
    private Journey journey;
    @Data
    public static class Journey{
        private String boardingPoint;
        private String endPoint;
        private LocalTime startTime;
        private LocalTime endTime;
        private Date travelDate;
    }
}
