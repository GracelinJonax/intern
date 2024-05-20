package com.example.bookingservice.Repository.Service;

import com.example.bookingservice.Model.BusDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusDetailsRepoService {
    List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String boardingPoint, String endPoint);

}
