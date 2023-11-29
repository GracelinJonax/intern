package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.orderBillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bill",url = "localhost:8081")
@Async
public interface billFeign {
    @PostMapping("/bill")
   void saveBill(@RequestBody orderBillDto orderBillDto);
}
