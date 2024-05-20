package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.BookingDetails;
import com.example.bookingservice.model.Journey;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BookingDetailsRepoService {
    List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey);

}
