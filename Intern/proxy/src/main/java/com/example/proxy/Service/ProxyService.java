package com.example.proxy.Service;

import com.example.proxy.Dto.orderBillDto;
import org.springframework.stereotype.Service;

@Service
public interface ProxyService {
    void saveBillService(orderBillDto orderBillDto);
}
