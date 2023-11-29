package com.db.mongodb.repository;

import com.db.mongodb.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepo extends MongoRepository<GroceryItem,String> {
}
