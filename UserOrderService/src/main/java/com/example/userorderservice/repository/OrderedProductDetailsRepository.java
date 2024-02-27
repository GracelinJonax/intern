package com.example.userorderservice.repository;

import com.example.userorderservice.model.OrderedProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductDetailsRepository extends JpaRepository<OrderedProductDetails, String> {
}
