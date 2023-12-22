package com.example.bookingservice.Api;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface bookingApi {

    @PostMapping("/saveUser")
    ResponseEntity<String> saveUser(@RequestBody UserDetails userDetails);

    @PostMapping("/saveBus")
    ResponseEntity<String> saveBus(@RequestBody BusDto busDto);

    @GetMapping("/getAllBus")
    ResponseEntity<List<BusDetails>> getAllBus(@RequestBody TravelDto travelDto);

    @GetMapping("/getBusLayout")
    ResponseEntity<LayoutDto> getBusLayout(@RequestBody getLayoutDto getLayoutDto);

    @PostMapping("/blockSeat")
    ResponseEntity<String> blockSeat(@RequestBody BlockDto blockDto);
    @PostMapping("/makePayment")
    ResponseEntity<TicketDto> makePayment(@RequestBody PaymentDto payment);

}
