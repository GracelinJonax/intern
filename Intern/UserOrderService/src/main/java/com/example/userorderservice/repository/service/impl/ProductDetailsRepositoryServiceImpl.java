package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.ProductDetailsRepository;
import com.example.userorderservice.repository.service.ProductDetailsRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsRepositoryServiceImpl implements ProductDetailsRepositoryService {
    public final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsRepositoryServiceImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }
}
