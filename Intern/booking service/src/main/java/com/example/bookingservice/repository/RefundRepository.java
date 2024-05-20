package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund,String> {
}
