package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.Offers;
import com.example.bookingservice.repository.OffersRepository;
import com.example.bookingservice.repository.service.OffersRepoService;
import org.springframework.stereotype.Service;

@Service
public class OffersRepoServiceImpl implements OffersRepoService {
    private final OffersRepository offersRepository;

    public OffersRepoServiceImpl(OffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }

    @Override
    public Offers findRandom(String type) {
        return offersRepository.findRandom(type);
    }
}
