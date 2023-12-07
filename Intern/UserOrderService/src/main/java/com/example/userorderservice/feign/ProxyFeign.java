package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.orderBillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "proxy", url = "localhost:8082")
public interface ProxyFeign {
    @PostMapping("/toBill")
    ResponseEntity<String> saveBill(@RequestBody orderBillDto orderBillDto);
}
