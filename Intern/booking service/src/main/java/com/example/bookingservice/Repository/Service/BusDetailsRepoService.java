package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.Journey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusDetailsRepoService {
    List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String BoardingPoint, String EndPoint);
    Journey findByIdAndJourneysBoardingPointAndJourneysEndPoint(String id, String BoardingPoint, String EndPoint);

}
