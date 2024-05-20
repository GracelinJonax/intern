package com.example.bookingservice.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class BusDetails {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private int totalSeats;
    private List<Seat> seat;
    List<Journey> journeys;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    @Data
    public static class Seat {
        private String seatType;
        private String seatRange;
        private Price price;
        private String femaleSeats;

        @Data
        public static class Price {
            private double adultPrice;
            private double childPrice;
        }
    }
}
