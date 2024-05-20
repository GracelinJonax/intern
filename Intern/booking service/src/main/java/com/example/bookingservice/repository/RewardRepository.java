package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.Reward;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByUserDetails(UserDetails user);

    List<Reward> findByStatusIsNot(String status);
}
