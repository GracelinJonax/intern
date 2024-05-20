package com.example.bookingservice.repository;

import com.example.bookingservice.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, String> {
    Journey findByBoardingPointAndEndPointAndStartTimeAndEndTime(String boardingPoint, String endPoint, LocalTime startTime, LocalTime endTime);

    List<Journey> findByBoardingPointAndEndPoint(String boardingPoint, String endPoint);
}
