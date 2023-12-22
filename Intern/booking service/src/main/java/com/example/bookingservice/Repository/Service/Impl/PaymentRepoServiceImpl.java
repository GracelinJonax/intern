package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.BookingDetails;
import com.example.bookingservice.Model.Payment;
import com.example.bookingservice.Repository.PaymentRepository;
import com.example.bookingservice.Repository.Service.PaymentRepoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepoServiceImpl implements PaymentRepoService {
    private final PaymentRepository paymentRepository;
    public PaymentRepoServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }

    @Override
    public Payment findByPNR(String pnr) {
        return paymentRepository.findByPNR(pnr);
    }
}
