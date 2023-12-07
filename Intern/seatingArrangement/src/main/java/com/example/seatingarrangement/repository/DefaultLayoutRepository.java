package com.example.seatingarrangement.repository;

import com.example.seatingarrangement.model.DefaultLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultLayoutRepository extends MongoRepository<DefaultLayout,String> {
}
