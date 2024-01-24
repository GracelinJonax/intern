package com.example.proxy.Feign;

import com.example.proxy.Dto.orderBillDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FeignService implements BillFeign {
    @Resource
    BillFeign billFeign;

    @Override
    public void saveBill(orderBillDto orderBillDto) {
         billFeign.saveBill(orderBillDto);
    }
}
