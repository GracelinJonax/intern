package com.example.userorderservice.api;

import com.example.userorderservice.Dto.orderBillDto;
import com.example.userorderservice.Dto.orderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.userDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1")
public interface userOrderApi {
    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody userDetails userDetails);
    @PostMapping("/product")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDetails productDetails);
    @PostMapping("/order")
    public ResponseEntity<String> orderProduct(@RequestBody orderDto orderDto);

}
