package com.example.billservice.api;

import com.example.billservice.Dto.orderBillDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public interface billApi {
    @PostMapping("/bill")
    public String saveBill(@RequestBody orderBillDto orderBillDto) throws ExecutionException;
}
