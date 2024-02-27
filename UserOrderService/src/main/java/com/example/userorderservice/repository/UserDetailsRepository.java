package com.example.userorderservice.repository;

import com.example.userorderservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    UserDetails findByEmail(String email);
}
