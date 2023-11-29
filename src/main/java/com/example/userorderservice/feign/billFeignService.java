package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.orderBillDto;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class billFeignService implements billFeign{
    @Resource
    billFeign billFeign;
    @Override
    public void saveBill(orderBillDto orderBillDto) {
        billFeign.saveBill(orderBillDto);
    }
}
