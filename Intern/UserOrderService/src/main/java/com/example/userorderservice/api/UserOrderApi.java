package com.example.userorderservice.api;

import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.UserDetails;
import com.example.userorderservice.util.ApplicationConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserOrderApi {
    @PostMapping(value = ApplicationConstants.userSaveApi)
    ResponseEntity<String> saveUser(@RequestBody UserDetails userDetails);

    @PostMapping(value = ApplicationConstants.productSaveApi)
    ResponseEntity<String> saveProduct(@RequestBody ProductDetails productDetails);

    @PostMapping(value = ApplicationConstants.orderSaveApi)
    ResponseEntity<String> orderProduct(@RequestBody OrderDto orderDto);
    //    @PostMapping("/email")
    //    void sendEmail(@RequestBody orderBillDto orderBillDto);

}
