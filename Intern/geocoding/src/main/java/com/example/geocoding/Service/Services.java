package com.example.geocoding.Service;

import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Dto.SaveStoreDto;
import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services {
    List<Store> saveStoreService(SaveStoreDto storeDto);
    List<Store> findNearStoreService(DistanceDto distanceDto);

    List<Company> saveCompanyService(Company company);
}
