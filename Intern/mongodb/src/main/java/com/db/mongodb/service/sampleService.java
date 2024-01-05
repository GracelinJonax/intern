package com.db.mongodb.service;

import com.db.mongodb.model.GroceryItem;
import com.db.mongodb.model.Price;
import com.db.mongodb.repository.GroceryRepo;
import com.db.mongodb.repository.priceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class sampleService {
    @Autowired
    GroceryRepo groceryRepo;
    @Autowired
    priceRepo priceRepo;
    @Cacheable("first")
    public String saveGroceryService(GroceryItem gi) {
        groceryRepo.save(gi);
        return "success";
    }
    @CacheEvict(value = "first",allEntries = true)
    public List<GroceryItem> getGroceryService() {
        return groceryRepo.findAll();
    }
    @CachePut(value = "first",condition = "#id=='2'")
    public String savePriceService(String id, Price price) {
        priceRepo.save(price);
        GroceryItem gi =groceryRepo.findById(id).get();
        gi.setPrice(price);
        groceryRepo.save(gi);
        return "successful";
    }
    @Cacheable("first")
    public String updateGroceryService(GroceryItem gi) {
//        GroceryItem g=groceryRepo.findById(gi.getId()).get();
        groceryRepo.save(gi);
        return "updated";
    }
    @Cacheable("first")
    public String deleteGroceryService(String id) {
        groceryRepo.deleteById(id);
        return "deleted";
    }

    public Price savePriceeService(Price price) {

    }
}
