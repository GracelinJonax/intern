package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Passengers;
import com.example.bookingservice.Repository.PassengersRepository;
import com.example.bookingservice.Repository.Service.PassengersRepoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengersRepoServiceImpl implements PassengersRepoService {
    private final PassengersRepository passengersRepository;
    public PassengersRepoServiceImpl(PassengersRepository passengersRepository){
        this.passengersRepository=passengersRepository;
    }

    @Override
    public List<Passengers> findByBookingDetailsAndSeatStatus(BookingDetails bookingDetails, String seatStatus) {
        return passengersRepository.findByBookingDetailsAndSeatStatus(bookingDetails,seatStatus);
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
