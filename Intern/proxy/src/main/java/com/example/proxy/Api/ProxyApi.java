package com.example.proxy.Api;

import com.example.proxy.Dto.orderBillDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public interface ProxyApi {
    @PostMapping("${billApi}")
    void saveBill(@RequestBody orderBillDto orderBillDto);
}
