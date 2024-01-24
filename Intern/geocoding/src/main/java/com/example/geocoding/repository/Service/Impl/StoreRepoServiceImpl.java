package com.example.geocoding.repository.Service.Impl;

import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import com.example.geocoding.repository.Service.StoreRepoService;
import com.example.geocoding.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreRepoServiceImpl implements StoreRepoService {
    private final StoreRepository storeRepository;
    public StoreRepoServiceImpl(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }

    @Override
    public List<Store> findStore(double latitude, double longitude, int distance, Company company) {
        return storeRepository.findStore(longitude,longitude,distance,company);
    }
}
