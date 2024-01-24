package com.example.userorderservice.controller;

import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.api.UserOrderApi;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.UserDetails;
import com.example.userorderservice.service.impl.userOrderServiceImpl;
import com.example.userorderservice.service.userOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOrderController implements UserOrderApi {

    public final userOrderService userOrderService;

    public UserOrderController(userOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @Override
    public ResponseEntity<String> saveUser(UserDetails userDetails) {
        return new ResponseEntity<>(userOrderService.saveUserService(userDetails), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> orderProduct(OrderDto orderDto) {
        return new ResponseEntity<>(userOrderService.orderProductService(orderDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveProduct(ProductDetails productDetails) {
        return new ResponseEntity<>(userOrderService.saveProductService(productDetails), HttpStatus.OK);
    }

    //    @Override
    //    public void sendEmail(orderBillDto orderBillDto) {
    //        userOrderService.sendEmailService(orderBillDto);
    //    }
}

