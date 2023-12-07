package com.db.mongodb.repository;

import com.db.mongodb.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface priceRepo extends MongoRepository<Price,String> {
}
