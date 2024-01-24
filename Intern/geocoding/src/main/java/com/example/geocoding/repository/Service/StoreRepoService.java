package com.example.geocoding.repository.Service;

import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreRepoService{
    List<Store> findStore(double latitude, double longitude, int distance, Company company);
}
