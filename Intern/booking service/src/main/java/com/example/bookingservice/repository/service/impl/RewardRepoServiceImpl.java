package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.Reward;
import com.example.bookingservice.model.UserDetails;
import com.example.bookingservice.repository.RewardRepository;
import com.example.bookingservice.repository.service.RewardRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRepoServiceImpl implements RewardRepoService {
    private final RewardRepository rewardRepository;

    public RewardRepoServiceImpl(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @Override
    public List<Reward> findByUserDetails(UserDetails user) {
        return rewardRepository.findByUserDetails(user);
    }

    @Override
    public List<Reward> findByStatusIsNot(String status) {
        return rewardRepository.findByStatusIsNot(status);
    }
}
