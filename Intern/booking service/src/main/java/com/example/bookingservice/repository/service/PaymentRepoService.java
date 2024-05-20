package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRepoService {
    Payment findByPNR(String pnr);
}
