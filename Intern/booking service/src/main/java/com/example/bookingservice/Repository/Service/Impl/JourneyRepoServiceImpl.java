package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.Journey;
import com.example.bookingservice.Repository.JourneyRepository;
import com.example.bookingservice.Repository.Service.JourneyRepoService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class JourneyRepoServiceImpl implements JourneyRepoService {
    private final JourneyRepository journeyRepository;
    public JourneyRepoServiceImpl(JourneyRepository journeyRepository){
        this.journeyRepository=journeyRepository;
    }
    @Override
    public List<Journey> findByBoardingPointAndEndPoint(String BoardingPoint, String EndPoint) {
        return journeyRepository.findByBoardingPointAndEndPoint(BoardingPoint,EndPoint);
    }

    @Override
    public Journey findByBoardingPointAndEndPointAndStartTimeAndEndTime(String BoardingPoint, String EndPoint, LocalTime startTime, LocalTime endTime) {
        return journeyRepository.findByBoardingPointAndEndPointAndStartTimeAndEndTime(BoardingPoint,EndPoint,startTime,endTime);
    }
}
