package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.service.userDetailsRepositoryService;
import com.example.userorderservice.repository.userDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class userDetailsRepositoryServiceImpl implements userDetailsRepositoryService {
    public final userDetailsRepository userDetailsRepository;
public userDetailsRepositoryServiceImpl(userDetailsRepository userDetailsRepository){
    this.userDetailsRepository=userDetailsRepository;
}

}
