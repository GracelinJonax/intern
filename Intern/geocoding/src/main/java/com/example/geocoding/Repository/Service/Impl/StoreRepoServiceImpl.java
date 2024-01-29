package com.example.geocoding.Repository.Service.Impl;

import com.example.geocoding.Model.Store;
import com.example.geocoding.Repository.Service.StoreRepoService;
import com.example.geocoding.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreRepoServiceImpl implements StoreRepoService {
    private final StoreRepository storeRepository;
    public StoreRepoServiceImpl(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }

    @Override
    public List<Store> findStore(double latitude, double longitude, int distance, Long companyId) {
        return storeRepository.findStore(longitude,longitude,distance,companyId);
    }
}
