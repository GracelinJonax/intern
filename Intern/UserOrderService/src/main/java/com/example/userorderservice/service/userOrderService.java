package com.example.userorderservice.service;

import com.example.userorderservice.Dto.orderBillDto;
import com.example.userorderservice.Dto.orderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.userDetails;
import org.springframework.stereotype.Service;

@Service
public interface userOrderService {
    String saveUserService(userDetails userDetails);

    String orderProductService(orderDto orderDto);

    String saveProductService(ProductDetails productDetails);

    //    void sendEmailService(orderBillDto orderBillDto);
}
