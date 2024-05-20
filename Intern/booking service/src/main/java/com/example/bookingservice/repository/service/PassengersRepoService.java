package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengersRepoService {
    List<Passengers> findByBookingDetailsAndSeatStatus(BookingDetails bookingDetails,String seatStatus);
    List<Passengers> findBySeatStatus(String seatStatus);

    List<Passengers> findByBookingDetails(BookingDetails booking);
}
