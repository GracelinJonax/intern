package com.example.seatingarrangement.repository.Service.Impl;

import com.example.seatingarrangement.repository.DefaultLayoutRepository;
import com.example.seatingarrangement.repository.Service.DefaultLayoutRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class DefaultLayoutRepositoryServiceImpl implements DefaultLayoutRepositoryService {
    private final DefaultLayoutRepository defaultLayoutRepository;
    public  DefaultLayoutRepositoryServiceImpl(DefaultLayoutRepository defaultLayoutRepository){
        this.defaultLayoutRepository=defaultLayoutRepository;
    }
}
