package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.Rewards;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards,Long> {
    List<Rewards> findByUserDetails(UserDetails user);
    List<Rewards> findByStatusIsNot(String status);
}
