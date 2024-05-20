package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Journey;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BookingDetailsRepoService {
    List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey);

}
