package com.example.geocoding.Service.Impl;

import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Model.Store;
import com.example.geocoding.Service.Services;
import com.example.geocoding.repository.Service.StoreRepoService;
import com.example.geocoding.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class serviceImpl implements Services {
    private final StoreRepository storeRepository;
    private final StoreRepoService storeRepoService;
    public serviceImpl(StoreRepository storeRepository,StoreRepoService storeRepoService){
        this.storeRepository=storeRepository;
        this.storeRepoService=storeRepoService;
    }
    @Override
    public List<Store> saveStoreService(Store store) {
        if(store==null)
            throw new RuntimeException("no store present");
        else if(store.getName().length()>50)
            throw new RuntimeException("store length is too long");
        else if(!(store.getLatitude()>=-90&&store.getLatitude()<=90)||!(store.getLongitude()>=-180&&store.getLongitude()<=180))
            throw new RuntimeException("check your store latitude and longitude");
        storeRepository.save(store);
        return storeRepository.findAll();
    }

    @Override
    public List<Store> findNearStoreService(DistanceDto distanceDto) {
        return storeRepoService.findStore(distanceDto.getLatitude(),distanceDto.getLongitude(),distanceDto.getDistance());
    }
}
