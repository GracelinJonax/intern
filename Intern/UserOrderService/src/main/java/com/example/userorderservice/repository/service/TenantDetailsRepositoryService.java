package com.example.userorderservice.repository.service;

import com.example.userorderservice.model.TenantDetails;
import com.example.userorderservice.model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface TenantDetailsRepositoryService {
    TenantDetails findByEmail(String email);
}
