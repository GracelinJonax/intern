package com.example.bookingservice.repository;

import com.example.bookingservice.model.BusDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusDetailsRepository extends MongoRepository<BusDetails, String> {
    List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String boardingPoint, String endPoint);
}
