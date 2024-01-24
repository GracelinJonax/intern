package com.example.userorderservice.repository.service.impl;

import com.example.userorderservice.repository.service.UserDetailsRepositoryService;
import com.example.userorderservice.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRepositoryServiceImpl implements UserDetailsRepositoryService {
    public final UserDetailsRepository userDetailsRepository;

    public UserDetailsRepositoryServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

}
