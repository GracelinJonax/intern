package com.example.userorderservice.service;

import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface userOrderService {
    String saveUserService(UserDetails userDetails);

    String orderProductService(OrderDto orderDto);

    String saveProductService(ProductDetails productDetails);

    //    void sendEmailService(orderBillDto orderBillDto);
}
