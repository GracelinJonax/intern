package com.example.geocoding.Service.Impl;

import com.example.geocoding.Model.Store;
import com.example.geocoding.Service.Services;
import com.example.geocoding.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class serviceImpl implements Services {
    private final StoreRepository storeRepository;
    public serviceImpl(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }
    @Override
    public List<Store> saveStoreService(Store store) {
        storeRepository.save(store);
        return storeRepository.findAll();
    }
}
