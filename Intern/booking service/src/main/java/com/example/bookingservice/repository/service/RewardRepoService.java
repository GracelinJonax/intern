package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.Reward;
import com.example.bookingservice.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RewardRepoService {
    List<Reward> findByUserDetails(UserDetails user);
    List<Reward> findByStatusIsNot(String status);
}
