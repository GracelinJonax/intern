package com.example.userorderservice.feign;

import com.example.userorderservice.Dto.OrderBillDto;
import com.example.userorderservice.util.ApplicationConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ApplicationConstants.feignName, url = ApplicationConstants.feignUrl)
@Async
public interface ProxyFeign {

    @PostMapping(value = ApplicationConstants.feignEndPoint)
    void saveBill(@RequestBody OrderBillDto orderBillDto);
}
