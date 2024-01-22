package com.example.geocoding.Api;

import com.example.geocoding.Model.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface Api {
    @PostMapping("/store")
    public ResponseEntity<List<Store>> saveStore(@RequestBody Store store);
}
