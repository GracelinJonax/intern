package com.example.geocoding.Service;

import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services {
    List<Store> saveStoreService(Store store);
    List<Store> findNearStoreService(DistanceDto distanceDto);
}
