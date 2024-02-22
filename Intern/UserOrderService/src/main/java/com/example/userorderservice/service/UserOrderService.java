package com.example.userorderservice.service;

import com.example.userorderservice.Dto.LoginDto;
import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.TenantDetails;
import com.example.userorderservice.model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserOrderService {
    String saveUserService(UserDetails userDetails);

    String orderProductService(OrderDto orderDto);

    String saveProductService(ProductDetails productDetails);

    String loginTenantService(LoginDto loginDto);

    boolean isTenantValid(String tenantId);

    String loginUserService(LoginDto loginDto);

    String saveTenantService(TenantDetails tenantDetails);

    //    void sendEmailService(orderBillDto orderBillDto);
}
