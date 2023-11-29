package com.example.cart.Configuration.datasource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "firstfeign", url="localhost:8080" )
public interface Employee {
    @GetMapping("/allDepartment")
    public ResponseEntity<String> allEmp();
}
