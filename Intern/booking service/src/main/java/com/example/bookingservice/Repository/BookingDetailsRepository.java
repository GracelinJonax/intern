package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails,String> {
    List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey);
}
