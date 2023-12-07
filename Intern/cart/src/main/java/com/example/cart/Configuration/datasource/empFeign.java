package com.example.cart.Configuration.datasource;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class empFeign implements Employee{
    @Resource
    Employee employee;

    @Override
    public ResponseEntity<String> allEmp() {
       return employee.allEmp();
    }
}
