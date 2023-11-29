package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.productDetailsRepository;
import com.example.userorderservice.repository.service.productDetailsRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class productDetailsRepositoryServiceImpl implements productDetailsRepositoryService {
    public final productDetailsRepository productDetailsRepository;
    public productDetailsRepositoryServiceImpl(productDetailsRepository productDetailsRepository){
        this.productDetailsRepository=productDetailsRepository;
    }
}
