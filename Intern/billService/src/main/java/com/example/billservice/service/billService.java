package com.example.billservice.service;

import com.example.billservice.Dto.orderBillDto;
import org.springframework.stereotype.Service;

@Service
public interface billService {

    String saveBillService(orderBillDto orderBillDto);
}
