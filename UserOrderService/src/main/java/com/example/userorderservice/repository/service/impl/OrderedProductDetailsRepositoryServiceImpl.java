package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.OrderedProductDetailsRepository;
import com.example.userorderservice.repository.service.OrderedProductDetailsRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductDetailsRepositoryServiceImpl implements OrderedProductDetailsRepositoryService {
    public final OrderedProductDetailsRepository orderedProductDetailsRepository;

    public OrderedProductDetailsRepositoryServiceImpl(OrderedProductDetailsRepository orderedProductDetailsRepository) {
        this.orderedProductDetailsRepository = orderedProductDetailsRepository;
    }
}
