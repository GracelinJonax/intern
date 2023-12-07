package com.example.billservice.repository;

import com.example.billservice.model.OrderedProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends MongoRepository<OrderedProduct, String> {
}
