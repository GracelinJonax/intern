package com.example.userorderservice.repository;

import com.example.userorderservice.model.TenantDetails;
import com.example.userorderservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantDetailsRepository extends JpaRepository<TenantDetails, String> {
    TenantDetails findByEmail(String email);
}
