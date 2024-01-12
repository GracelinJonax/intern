package com.example.bookingservice.Api;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.RewardInformation;
import com.example.bookingservice.Model.Rewards;
import com.example.bookingservice.Model.UserDetails;
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
    @PostMapping("/rewardInformation")
    ResponseEntity<List<RewardInformation>> saveRewardInformation(@RequestBody RewardInformation rewardInformation);
    @GetMapping("/getReward/{id}")
    ResponseEntity<Rewards> getReward(@PathVariable Long id);
//    @PostMapping("/claimReward/{rewardId}")
//    ResponseEntity<Rewards>claimReward(@PathVariable Long id);
    @GetMapping("/getUserReward/{userId}")
    ResponseEntity<List<Rewards>> getUserReward(@PathVariable String userId);
}
