package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.Rewards;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RewardsRepoService{
    List<Rewards> findByUserDetails(UserDetails user);
    List<Rewards> findByStatusIsNot(String status);
}
