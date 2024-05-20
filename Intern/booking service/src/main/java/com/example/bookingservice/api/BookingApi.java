package com.example.bookingservice.api;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.model.BusDetails;
import com.example.bookingservice.model.Links;
import com.example.bookingservice.model.Offers;
import com.example.bookingservice.model.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface BookingApi {

    @PostMapping("/saveUser")
    ResponseEntity<String> saveUser(@RequestBody UserDetails userDetails);

    @PostMapping("/saveBus")
    ResponseEntity<String> saveBus(@RequestBody BusDto busDto);

    @GetMapping("/getAllBus")
    ResponseEntity<List<BusDetails>> getAllBus(@RequestBody TravelDto travelDto);

    @GetMapping("/getBusLayout")
    ResponseEntity<LayoutDto> getBusLayout(@RequestBody GetLayoutDto getLayoutDto);

    @PostMapping("/blockSeat")
    ResponseEntity<String> blockSeat(@RequestBody BlockDto blockDto);

    @PostMapping("/makePayment")
    ResponseEntity<RewardDto> makePayment(@RequestBody PaymentDto payment);

    @PostMapping("/cancel")
    ResponseEntity<String> cancelBooking(@RequestBody CancelDto cancelDto);

    @PostMapping("/offer")
    ResponseEntity<List<Offers>> saveRewardInformation(@RequestBody Offers offers);

    @PostMapping("/link")
    ResponseEntity<List<Links>> saveLinks(@RequestBody Links links);

    @GetMapping("/getReward/{id}")
    ResponseEntity<OfferDto> getReward(@PathVariable Long id);

    @GetMapping("/getUserReward/{userId}")
    ResponseEntity<List<OfferDto>> getUserReward(@PathVariable String userId);

}
