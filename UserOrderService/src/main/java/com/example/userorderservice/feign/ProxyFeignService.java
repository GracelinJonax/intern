package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.OrderBillDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProxyFeignService implements ProxyFeign {
    @Resource
    ProxyFeign ProxyFeign;

    @Override
    public void saveBill(OrderBillDto orderBillDto) {
         ProxyFeign.saveBill(orderBillDto);
    }
}
