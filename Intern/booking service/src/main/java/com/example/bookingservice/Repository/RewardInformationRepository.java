package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.RewardInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardInformationRepository extends JpaRepository<RewardInformation,Long> {
}
