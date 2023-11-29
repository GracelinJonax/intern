package com.example.billservice.controller;

import com.example.billservice.Dto.orderBillDto;
import com.example.billservice.api.billApi;
import com.example.billservice.model.Bill;
import com.example.billservice.service.billService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class billController implements billApi {
    private final billService billService;

    public billController(billService billService){
        this.billService=billService;
    }

    @Override
    public void saveBill(orderBillDto orderBillDto) {
                billService.saveBillService(orderBillDto);
    }
}
