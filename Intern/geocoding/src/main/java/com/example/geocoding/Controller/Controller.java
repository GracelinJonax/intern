package com.example.geocoding.Controller;

import com.example.geocoding.Api.Api;
import com.example.geocoding.Dto.*;
import com.example.geocoding.Model.*;
import com.example.geocoding.Service.Services;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller implements Api {
    private final Services services;

    public Controller(Services services) {
        this.services = services;
    }

    @Override
    public ResponseEntity<List<Store>> saveStore(SaveStoreDto storeDto) {
        return new ResponseEntity<>(services.saveStoreService(storeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Company>> saveCompany(SaveCompanyDto companyDto) {
        return new ResponseEntity<>(services.saveCompanyService(companyDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StoreCompanyView>> findNearStore(DistanceDto distanceDto) {
        return new ResponseEntity<>(services.findNearStoreService(distanceDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Plan>> savePlan(SavePlanDto savePlanDto) {
        return new ResponseEntity<>(services.savePlanServices(savePlanDto),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubscriptionView>> saveSubscription(SubscriptionDto subscriptionDto) {
        return new ResponseEntity<>(services.saveSubscriptionService(subscriptionDto),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> login() {
        String token= Jwts.builder().subject("1").compact();
//        String token="hai";
//        return "hai";
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
