package com.example.geocoding.Controller;

import com.example.geocoding.Api.Api;
import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Model.Store;
import com.example.geocoding.Service.Services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller implements Api {
    private final Services services;
    public Controller(Services services){
        this.services=services;
    }
    @Override
    public ResponseEntity<List<Store>> saveStore(Store store) {
        return new ResponseEntity<>(services.saveStoreService(store),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Store>> findNearStore(DistanceDto distanceDto) {
        return new ResponseEntity<>(services.findNearStoreService(distanceDto),HttpStatus.OK);
    }
}
