package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.Offers;
import com.example.bookingservice.Repository.OffersRepository;
import com.example.bookingservice.Repository.Service.OffersRepoService;
import org.springframework.stereotype.Service;

@Service
public class OffersRepoServiceImpl implements OffersRepoService {
    private final OffersRepository offersRepository;
    public OffersRepoServiceImpl(OffersRepository offersRepository){
        this.offersRepository = offersRepository;
    }

    @Override
    public Offers findRandom(String type) {
        return offersRepository.findRandom(type);
    }
}
