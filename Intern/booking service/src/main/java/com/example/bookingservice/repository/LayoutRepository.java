package com.example.bookingservice.repository;

import com.example.bookingservice.model.Layout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends MongoRepository<Layout, String> {
}
