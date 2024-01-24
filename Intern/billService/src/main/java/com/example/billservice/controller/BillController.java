package com.example.billservice.controller;

import com.example.billservice.Dto.OrderBillDto;
import com.example.billservice.api.BillApi;
import com.example.billservice.service.BillService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController implements BillApi {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void saveBill(OrderBillDto orderBillDto){
             billService.saveBillService(orderBillDto);
    }
}
