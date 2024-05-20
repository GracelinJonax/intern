package com.db.mongodb.api;

import com.db.mongodb.model.Counter;
import com.db.mongodb.model.GroceryItem;
import com.db.mongodb.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.event.PaintEvent;
import java.util.List;
import java.util.Map;

public interface sample {
    @PostMapping("/save")
    public ResponseEntity<String> saveGrocery(@RequestBody GroceryItem gi);
    @GetMapping("/get")
    public ResponseEntity<List<GroceryItem>> getGrocery();
    @PostMapping("/price/{id}")
    public ResponseEntity<String> savePrice(@PathVariable String id,@RequestBody Price price);
    @PutMapping("/update")
    public ResponseEntity<String> updateGrocery(@RequestBody  GroceryItem gi);
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGrocery(@PathVariable String id);
    @GetMapping("/find")
    ResponseEntity<List<Map<String,Object>>> findGrocery(@RequestBody String name);
    @GetMapping("/findRegex")
    ResponseEntity<GroceryItem> findGroceryRegex(@RequestBody String name);
    @GetMapping("/findOne")
    ResponseEntity<List<Map<String,Object>>> findGroceryCount(@RequestBody String name);
//    ResponseEntity<Counter> findGroceryCount(@RequestBody String name,String newOne);
//    @PostMapping("/price")
//    ResponseEntity<Price> savePricee(@RequestBody Price price);
}
