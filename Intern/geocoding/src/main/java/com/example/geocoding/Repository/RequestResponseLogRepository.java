package com.example.geocoding.Repository;

import com.example.geocoding.Model.RequestResponseLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface RequestResponseLogRepository extends MongoRepository<RequestResponseLog,String> {
    Optional<RequestResponseLog> findTopBySubscriptionId(String subscriptionId);
    Long countBySubscriptionId(String subscriptionId);
    Long countBySubscriptionIdAndDateLike(String subscriptionId,LocalDate date);
}
