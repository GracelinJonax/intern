package com.example.bookingservice.repository;

import com.example.bookingservice.model.BookingDetails;
import com.example.bookingservice.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails,String> {
    List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey);
}
