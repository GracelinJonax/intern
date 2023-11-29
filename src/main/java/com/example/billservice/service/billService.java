package com.example.billservice.service;

import com.example.billservice.Dto.orderBillDto;
import com.example.billservice.model.Bill;
import org.springframework.stereotype.Service;

@Service
public interface billService {

    void saveBillService(orderBillDto orderBillDto);
}
