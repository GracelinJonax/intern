package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Journey;
import com.example.bookingservice.Repository.BookingDetailsRepository;
import com.example.bookingservice.Repository.Service.BookingDetailsRepoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingDetailsRepoServiceImpl implements BookingDetailsRepoService {
    private final BookingDetailsRepository bookingDetailsRepository;
    public BookingDetailsRepoServiceImpl(BookingDetailsRepository bookingDetailsRepository){
        this.bookingDetailsRepository=bookingDetailsRepository;
    }
    @Override
    public List<BookingDetails> findAllByTravelDateAndJourney(Date date, Journey journey) {
        return bookingDetailsRepository.findAllByTravelDateAndJourney(date,journey);
    }
}
