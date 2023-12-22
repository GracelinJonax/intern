package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Payment findByPNR(String pnr);
}
