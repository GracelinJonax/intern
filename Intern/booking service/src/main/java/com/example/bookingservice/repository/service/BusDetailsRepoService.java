package com.example.bookingservice.repository.service;

import com.example.bookingservice.model.BusDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusDetailsRepoService {
    List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String boardingPoint, String endPoint);

}
