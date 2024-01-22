package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.Reward;
import com.example.bookingservice.Model.UserDetails;
import com.example.bookingservice.Repository.RewardRepository;
import com.example.bookingservice.Repository.Service.RewardRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRepoServiceImpl implements RewardRepoService {
    private final RewardRepository rewardRepository;
    public RewardRepoServiceImpl(RewardRepository rewardRepository){
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
