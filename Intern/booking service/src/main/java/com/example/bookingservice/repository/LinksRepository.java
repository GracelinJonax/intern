package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.Journey;
import com.example.bookingservice.Model.Links;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinksRepository extends MongoRepository<Links,String> {
    Links getFirstByPublishIsNot(String publish);
}
