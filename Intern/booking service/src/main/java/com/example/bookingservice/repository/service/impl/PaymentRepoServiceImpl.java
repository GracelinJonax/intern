package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.Payment;
import com.example.bookingservice.repository.PaymentRepository;
import com.example.bookingservice.repository.service.PaymentRepoService;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepoServiceImpl implements PaymentRepoService {
    private final PaymentRepository paymentRepository;

    public PaymentRepoServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findByPnr(String pnr) {
        return paymentRepository.findByPnr(pnr);
    }
}
