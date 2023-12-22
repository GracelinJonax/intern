package com.example.bookingservice.Repository.Service.Impl;

import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.Journey;
import com.example.bookingservice.Repository.BusDetailsRepository;
import com.example.bookingservice.Repository.Service.BusDetailsRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusDetailsRepoServiceImpl implements BusDetailsRepoService {
    private final BusDetailsRepository busDetailsRepository;
    public BusDetailsRepoServiceImpl(BusDetailsRepository busDetailsRepository){
        this.busDetailsRepository=busDetailsRepository;
    }
    @Override
    public List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String BoardingPoint, String EndPoint) {
        return busDetailsRepository.findByJourneysBoardingPointAndJourneysEndPoint(BoardingPoint,EndPoint);
    }

    @Override
    public Journey findByIdAndJourneysBoardingPointAndJourneysEndPoint(String id, String BoardingPoint, String EndPoint) {
        return busDetailsRepository.findByIdAndJourneysBoardingPointAndJourneysEndPoint(id,BoardingPoint,EndPoint);
    }
}
