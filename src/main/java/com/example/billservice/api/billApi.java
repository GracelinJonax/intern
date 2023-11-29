package com.example.billservice.api;

import com.example.billservice.Dto.orderBillDto;
import com.example.billservice.model.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface billApi {
    @PostMapping("/bill")
    public void saveBill(@RequestBody orderBillDto orderBillDto);
}
