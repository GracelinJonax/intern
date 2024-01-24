package com.example.billservice.service;

import com.example.billservice.Dto.OrderBillDto;
import org.springframework.stereotype.Service;

@Service
public interface BillService {

    void saveBillService(OrderBillDto orderBillDto);
}
