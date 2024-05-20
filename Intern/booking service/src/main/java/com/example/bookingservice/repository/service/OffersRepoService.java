package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.Offers;
import org.springframework.stereotype.Service;

@Service
public interface OffersRepoService {
    Offers findRandom(String type);
}
