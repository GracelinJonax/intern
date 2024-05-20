package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.BookingDetails;
import com.example.bookingservice.model.Passengers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengersRepoService {
    List<Passengers> findByBookingDetailsAndSeatStatus(BookingDetails bookingDetails,String seatStatus);
    List<Passengers> findBySeatStatus(String seatStatus);

    List<Passengers> findByBookingDetails(BookingDetails booking);
}
