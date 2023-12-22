package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.Layout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends MongoRepository<Layout,String> {
}
