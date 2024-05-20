package com.example.bookingservice.service;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.model.BusDetails;
import com.example.bookingservice.model.Links;
import com.example.bookingservice.model.Offers;
import com.example.bookingservice.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    void saveBusService(BusDto busDto);

    List<BusDetails> getAllBusService(TravelDto travelDto);

    LayoutDto getBusLayoutService(GetLayoutDto getLayoutDto);

    String blockSeatService(BlockDto blockDto);

    String saveUserService(UserDetails userDetails);

    RewardDto makePayment(PaymentDto payment);

    String cancelBookingService(CancelDto cancelDto);

    List<Offers> saveAllRewards(Offers offers);

    OfferDto getRewardService(Long id);

    List<OfferDto> getUserRewardService(String userId);

    List<Links> saveLinksService(Links links);
}
