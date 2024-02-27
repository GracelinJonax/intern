package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.model.TenantDetails;
import com.example.userorderservice.model.UserDetails;
import com.example.userorderservice.repository.TenantDetailsRepository;
import com.example.userorderservice.repository.UserDetailsRepository;
import com.example.userorderservice.repository.service.TenantDetailsRepositoryService;
import com.example.userorderservice.repository.service.UserDetailsRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantDetailsRepositoryServiceImpl implements TenantDetailsRepositoryService {
    @Autowired
    TenantDetailsRepository tenantDetailsRepository;

    @Override
    public TenantDetails findByEmail(String email) {
        return tenantDetailsRepository.findByEmail(email);
    }

}
