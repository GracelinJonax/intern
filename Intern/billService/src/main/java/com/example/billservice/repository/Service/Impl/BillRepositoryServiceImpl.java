package com.example.billservice.repository.Service.Impl;

import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.Service.BillRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class BillRepositoryServiceImpl implements BillRepositoryService {
    private final BillRepository billRepository;

    public BillRepositoryServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}
