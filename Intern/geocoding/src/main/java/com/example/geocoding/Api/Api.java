package com.example.geocoding.Api;

import com.example.geocoding.Dto.DistanceDto;
import com.example.geocoding.Dto.SaveStoreDto;
import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface Api {
    @PostMapping("${storeApi}")
    ResponseEntity<List<Store>> saveStore(@RequestBody SaveStoreDto storeDto);
    @PostMapping("${companyApi}")
    ResponseEntity<List<Company>> saveCompany(@RequestBody Company company);
    @GetMapping("${distanceApi}")
    ResponseEntity<List<Store>> findNearStore(@RequestBody DistanceDto distanceDto);
}
