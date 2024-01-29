package com.example.geocoding.Service;

import com.example.geocoding.Dto.*;
import com.example.geocoding.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services {
    List<Store> saveStoreService(SaveStoreDto storeDto);
    List<Company> saveCompanyService(SaveCompanyDto companyDto);
    List<StoreCompanyView> findNearStoreService(DistanceDto distanceDto);

    List<Plan> savePlanServices(SavePlanDto savePlanDto);

    List<SubscriptionView> saveSubscriptionService(SubscriptionDto subscriptionDto);
}
