package com.example.geocoding.repository.Service.Impl;

import com.example.geocoding.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreRepoServiceImpl {
    private final StoreRepository storeRepository;
    public StoreRepoServiceImpl(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }
}
