package com.example.bookingservice.Api;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<RewardDto> makePayment(@RequestBody PaymentDto payment);

    @PostMapping("/cancel")
    ResponseEntity<String> cancelBooking(@RequestBody CancelDto cancelDto);
    @PostMapping("/offer")
    ResponseEntity<List<Offers>> saveRewardInformation(@RequestBody Offers offers);
    @PostMapping("/link")
    ResponseEntity<List<Links>> saveLinks(@RequestBody Links links);
    @GetMapping("/getReward/{id}")
    ResponseEntity<OfferDto> getReward(@PathVariable Long id);
//    @PostMapping("/claimReward/{rewardId}")
//    ResponseEntity<Rewards>claimReward(@PathVariable Long id);
    @GetMapping("/getUserReward/{userId}")
    ResponseEntity<List<OfferDto>> getUserReward(@PathVariable String userId);

}
