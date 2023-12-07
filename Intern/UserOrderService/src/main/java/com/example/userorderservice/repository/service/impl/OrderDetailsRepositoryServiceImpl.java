package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.OrderDetailsRepository;
import com.example.userorderservice.repository.service.OrderDetailsRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsRepositoryServiceImpl implements OrderDetailsRepositoryService {
    public final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsRepositoryServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }
}
