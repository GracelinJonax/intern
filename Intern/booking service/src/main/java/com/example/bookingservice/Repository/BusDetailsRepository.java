package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusDetailsRepository extends MongoRepository<BusDetails,String> {
    List<BusDetails> findByJourneysBoardingPointAndJourneysEndPoint(String BoardingPoint,String EndPoint);
    Journey findByIdAndJourneysBoardingPointAndJourneysEndPoint(String id,String BoardingPoint,String EndPoint);
}
