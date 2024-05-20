package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRepoService {
    Payment findByPnr(String pnr);
}
