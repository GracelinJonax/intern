package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.Journey;
import com.example.bookingservice.repository.JourneyRepository;
import com.example.bookingservice.repository.service.JourneyRepoService;
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
    public List<Journey> findByBoardingPointAndEndPoint(String boardingPoint, String endPoint) {
        return journeyRepository.findByBoardingPointAndEndPoint(boardingPoint,endPoint);
    }

    @Override
    public Journey findByBoardingPointAndEndPointAndStartTimeAndEndTime(String boardingPoint, String endPoint, LocalTime startTime, LocalTime endTime) {
        return journeyRepository.findByBoardingPointAndEndPointAndStartTimeAndEndTime(boardingPoint,endPoint,startTime,endTime);
    }
}
