package com.example.userorderservice.api;

import com.example.userorderservice.Dto.LoginDto;
import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.TenantDetails;
import com.example.userorderservice.model.UserDetails;
import com.example.userorderservice.util.ApplicationConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserOrderApi {
    @PostMapping(value = ApplicationConstants.productSaveApi)
    ResponseEntity<String> saveProduct(@RequestBody ProductDetails productDetails);

    @PostMapping(value = ApplicationConstants.orderSaveApi)
    ResponseEntity<String> orderProduct(@RequestBody OrderDto orderDto);

    @GetMapping(value = ApplicationConstants.loginTenantApi)
    ResponseEntity<String> loginTenant(@RequestBody LoginDto loginDto);

    @GetMapping(value = ApplicationConstants.loginUserApi)
    ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto);
    @PostMapping(value = ApplicationConstants.userSaveApi)
    ResponseEntity<String> saveUser(@RequestBody UserDetails userDetails);
    @PostMapping(value = ApplicationConstants.tenantSaveApi)
    ResponseEntity<String> saveTenant(@RequestBody TenantDetails tenantDetails);
    //    @PostMapping("/email")
    //    void sendEmail(@RequestBody orderBillDto orderBillDto);

}
