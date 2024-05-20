package com.db.mongodb.repository;

import com.db.mongodb.model.Counter;
import com.db.mongodb.model.GroceryItem;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GroceryRepo extends MongoRepository<GroceryItem, String> {
    @Query("{'name': {$regex: ?0}}")
    GroceryItem findingTheNameRegex(String name);

    @Query("{'name': ?0}")
    List<Map<String,Object>> findingTheName(String name);

    @Aggregation(pipeline = {"{'$match': {'name': {$regex: ?0}}}",
            "{'$count': 'count'}"
//            "{'$addFields': {newOne: ?1}}"
    })
    List<Map<String,Object>> findOne(String name);
}
