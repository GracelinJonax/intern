package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.Offers;
import org.springframework.stereotype.Service;

@Service
public interface OffersRepoService {
    Offers findRandom(String type);
}
