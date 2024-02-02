package com.example.geocoding.Repository;

import com.example.geocoding.Model.SubscriptionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SubscriptionViewRepository extends JpaRepository<SubscriptionView, String> {
    SubscriptionView findByCompanyIdAndExpiryDateAfter(String companyId, LocalDate expiryDate);

    SubscriptionView findBySubscriptionId(String subscriptionId);

    Optional<SubscriptionView> findBySubscriptionIdAndExpiryDateAfter(String subscriptionId, LocalDate expiryDate);

}
