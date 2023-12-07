package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.orderBillDto;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class proxyFeignService implements ProxyFeign {
    @Resource
    ProxyFeign ProxyFeign;

    @Override
    public ResponseEntity<String> saveBill(orderBillDto orderBillDto) {
        return ProxyFeign.saveBill(orderBillDto);
    }
}
