package com.example.billservice.api;

import com.example.billservice.Dto.OrderBillDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BillApi {
    @PostMapping("${billApi}")
    void saveBill(@RequestBody OrderBillDto orderBillDto);
}
