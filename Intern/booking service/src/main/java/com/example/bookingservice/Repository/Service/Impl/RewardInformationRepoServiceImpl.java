package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Repository.RewardInformationRepository;
import com.example.bookingservice.Repository.Service.RewardInformationRepoService;
import org.springframework.stereotype.Service;

@Service
public class RewardInformationRepoServiceImpl implements RewardInformationRepoService {
    private final RewardInformationRepository rewardInformationRepository;
    public RewardInformationRepoServiceImpl(RewardInformationRepository rewardInformationRepository){
        this.rewardInformationRepository=rewardInformationRepository;
    }
}
