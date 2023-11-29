package com.example.userorderservice.repository;

import com.example.userorderservice.model.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userDetailsRepository extends JpaRepository<userDetails,String> {
}
