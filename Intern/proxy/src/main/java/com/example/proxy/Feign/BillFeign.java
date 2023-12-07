package com.example.proxy.Feign;

import com.example.proxy.Dto.orderBillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bill", url = "localhost:8081")
public interface BillFeign {
    @PostMapping("/bill")
    String saveBill(@RequestBody orderBillDto orderBillDto);
}
