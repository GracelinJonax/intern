package com.db.mongodb.controller;

import com.db.mongodb.api.sample;
import com.db.mongodb.model.Counter;
import com.db.mongodb.model.GroceryItem;
import com.db.mongodb.model.Price;
import com.db.mongodb.repository.GroceryRepo;
import com.db.mongodb.service.sampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class sampleController implements sample {
    @Autowired
    sampleService sampleservice;
    @Override
    public ResponseEntity<String> saveGrocery(GroceryItem gi) {
        return new ResponseEntity<String>(sampleservice.saveGroceryService(gi),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<GroceryItem>> getGrocery() {
        return new ResponseEntity<>(sampleservice.getGroceryService(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteGrocery(String id) {
        return new ResponseEntity<>(sampleservice.deleteGroceryService(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateGrocery(GroceryItem gi) {
        return new ResponseEntity<>(sampleservice.updateGroceryService(gi),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> savePrice(String id, Price price) {
        return new ResponseEntity<>(sampleservice.savePriceService(id,price),HttpStatus.OK);
    }
@Autowired
    GroceryRepo repo;
    @Override
    public ResponseEntity<List<Map<String,Object>>> findGrocery(String name) {
        return new ResponseEntity<>(repo.findingTheName(name),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<GroceryItem> findGroceryRegex(String name) {
        return new ResponseEntity<>(repo.findingTheNameRegex(name),HttpStatus.OK);
    }@Override
    public ResponseEntity<List<Map<String,Object>>> findGroceryCount(String name) {
//        System.out.println(name+"*"+newOne);
//        System.out.println(repo.findOne(name));
        return new ResponseEntity<>(repo.findOne(name),HttpStatus.OK);
    }
//    @Override
//    public ResponseEntity<Price> savePricee(Price price) {
//        return new ResponseEntity<>(sampleservice.savePriceeService(price),HttpStatus.OK);
//    }
}
