package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.Reward;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RewardRepoService {
    List<Reward> findByUserDetails(UserDetails user);
    List<Reward> findByStatusIsNot(String status);
}
