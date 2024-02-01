package com.example.geocoding.Repository.Service;

import com.example.geocoding.Model.RequestResponseLog;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public interface RequestResponseLogRepoService {
    Long countBySubscriptionId(String subscriptionId);
    Long countBySubscriptionIdAndDateLike(String subscriptionId, LocalDate date);
Optional<RequestResponseLog> findTopBySubscriptionId(String subscriptionId);
}
