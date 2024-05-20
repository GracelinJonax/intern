package com.example.bookingservice.repository.service.impl;

import com.example.bookingservice.model.BusDetails;
import com.example.bookingservice.model.Journey;
import com.example.bookingservice.repository.BusDetailsRepository;
import com.example.bookingservice.repository.service.BusDetailsRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusDetailsRepoServiceImpl implements BusDetailsRepoService {
    private final BusDetailsRepository busDetailsRepository;

    public BusDetailsRepoServiceImpl(BusDetailsRepository busDetailsRepository) {
        this.busDetailsRepository = busDetailsRepository;
    }

    @Override
    public List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String boardingPoint, String endPoint) {
        return busDetailsRepository.findByJourneysBoardingPointAndJourneysEndPoint(boardingPoint, endPoint);
    }

}
