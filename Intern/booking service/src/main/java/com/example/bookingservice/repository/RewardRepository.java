package com.example.bookingservice.repository;

import com.example.bookingservice.model.Reward;
import com.example.bookingservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByUserDetails(UserDetails user);

    List<Reward> findByStatusIsNot(String status);
}
