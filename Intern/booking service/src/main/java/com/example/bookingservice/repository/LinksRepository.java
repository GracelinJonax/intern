package com.example.bookingservice.repository;

import com.example.bookingservice.model.Links;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends MongoRepository<Links, String> {
    Links getFirstByPublishIsNot(String publish);
}
