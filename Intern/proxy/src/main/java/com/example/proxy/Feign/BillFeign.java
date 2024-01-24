package com.example.proxy.Feign;

import com.example.proxy.Dto.orderBillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feignBill}", url = "${feignBillUrl}")
public interface BillFeign {
    @PostMapping("${feignBill}")
    void saveBill(@RequestBody orderBillDto orderBillDto);
}
