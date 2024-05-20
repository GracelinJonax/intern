package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengersRepository extends JpaRepository<Passengers,String> {
    List<Passengers> findByBookingDetailsAndSeatStatus(BookingDetails bookingDetails,String seatStatus);
    List<Passengers> findBySeatStatus(String seatStatus);
    List<Passengers> findByBookingDetails(BookingDetails booking);
}
