package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.Journey;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public interface JourneyRepoService {
    List<Journey> findByBoardingPointAndEndPoint(String BoardingPoint, String EndPoint);
    Journey findByBoardingPointAndEndPointAndStartTimeAndEndTime(String BoardingPoint, String EndPoint, LocalTime startTime, LocalTime endTime);


}
