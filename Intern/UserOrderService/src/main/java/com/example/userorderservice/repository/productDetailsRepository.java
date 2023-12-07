package com.example.userorderservice.repository;

import com.example.userorderservice.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productDetailsRepository extends JpaRepository<ProductDetails, String> {

}
