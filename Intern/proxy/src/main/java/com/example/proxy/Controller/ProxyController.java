package com.example.proxy.Controller;

import com.example.proxy.Api.ProxyApi;
import com.example.proxy.Dto.orderBillDto;
import com.example.proxy.Service.ProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController implements ProxyApi {
    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public void saveBill(orderBillDto orderBillDto) {
        proxyService.saveBillService(orderBillDto);
    }
}
