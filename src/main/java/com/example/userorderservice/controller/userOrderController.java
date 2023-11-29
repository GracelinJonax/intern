package com.example.userorderservice.controller;

import com.example.userorderservice.Dto.orderBillDto;
import com.example.userorderservice.Dto.orderDto;
import com.example.userorderservice.api.userOrderApi;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.userDetails;
import com.example.userorderservice.repository.userDetailsRepository;
import com.example.userorderservice.service.userOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userOrderController implements userOrderApi {

    public final userOrderService userOrderService;
    public userOrderController(userOrderService userOrderService){
        this.userOrderService=userOrderService;
    }
    @Override
    public ResponseEntity<String> saveUser(userDetails userDetails) {
        return new ResponseEntity<>(userOrderService.saveUserService(userDetails), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> orderProduct(orderDto orderDto) {
        return new ResponseEntity<>(userOrderService.orderProductService(orderDto),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveProduct(ProductDetails productDetails) {
        return new ResponseEntity<>(userOrderService.saveProductService(productDetails),HttpStatus.OK);
    }


    }

