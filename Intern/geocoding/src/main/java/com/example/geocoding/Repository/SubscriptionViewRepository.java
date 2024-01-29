package com.example.geocoding.Repository;

import com.example.geocoding.Model.SubscriptionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionViewRepository extends JpaRepository<SubscriptionView,String> {
}
