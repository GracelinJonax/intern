package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.BookingDetails;
import com.example.bookingservice.model.Journey;
import com.example.bookingservice.repository.BookingDetailsRepository;
import com.example.bookingservice.repository.service.BookingDetailsRepoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingDetailsRepoServiceImpl implements BookingDetailsRepoService {
    private final BookingDetailsRepository bookingDetailsRepository;

    public BookingDetailsRepoServiceImpl(BookingDetailsRepository bookingDetailsRepository) {
        this.bookingDetailsRepository = bookingDetailsRepository;
    }

    @Override
    public List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey) {
        return bookingDetailsRepository.findAllByTravelDateAndJourney(date, journey);
    }
}
