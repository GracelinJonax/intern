package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.Rewards;
import com.example.bookingservice.Model.UserDetails;
import com.example.bookingservice.Repository.RewardsRepository;
import com.example.bookingservice.Repository.Service.RewardsRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsRepoServiceImpl implements RewardsRepoService {
    private final RewardsRepository rewardsRepository;
    public RewardsRepoServiceImpl(RewardsRepository rewardsRepository){
        this.rewardsRepository=rewardsRepository;
    }

    @Override
    public List<Rewards> findByUserDetails(UserDetails user) {
        return rewardsRepository.findByUserDetails(user);
    }

    @Override
    public List<Rewards> findByStatusIsNot(String status) {
        return rewardsRepository.findByStatusIsNot(status);
    }
}
