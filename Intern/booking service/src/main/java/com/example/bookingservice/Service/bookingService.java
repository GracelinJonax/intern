package com.example.bookingservice.Service;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface bookingService {

    void saveBusService(BusDto busDto);

    List<BusDetails> getAllBusService(TravelDto travelDto);

    LayoutDto getBusLayoutService(getLayoutDto getLayoutDto);

    String blockSeatService(BlockDto blockDto);

    String saveUserService(UserDetails userDetails);

    RewardDto makePayment(PaymentDto payment);

    String cancelBookingSerice(CancelDto cancelDto);

    List<Offers> saveAllRewards(Offers offers);

    OfferDto getRewardService(Long id);

    List<OfferDto> getUserRewardService(String userId);

    List<Links> saveLinksService(Links links);
}
