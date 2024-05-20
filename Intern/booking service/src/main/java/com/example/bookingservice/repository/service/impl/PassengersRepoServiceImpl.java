package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.BookingDetails;
import com.example.bookingservice.model.Passengers;
import com.example.bookingservice.repository.PassengersRepository;
import com.example.bookingservice.repository.service.PassengersRepoService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengersRepoServiceImpl implements PassengersRepoService {
    private final PassengersRepository passengersRepository;

    public PassengersRepoServiceImpl(PassengersRepository passengersRepository) {
        this.passengersRepository = passengersRepository;
    }

    @Override
    public List<Passengers> findByBookingDetailsAndSeatStatus(BookingDetails bookingDetails, String seatStatus) {
        return passengersRepository.findByBookingDetailsAndSeatStatus(bookingDetails, seatStatus);
    }

    @Override
    public List<Passengers> findBySeatStatus(String seatStatus) {
        return passengersRepository.findBySeatStatus(seatStatus);
    }

    @Override
    public List<Passengers> findByBookingDetails(BookingDetails booking) {
        return passengersRepository.findByBookingDetails(booking);
    }
}
