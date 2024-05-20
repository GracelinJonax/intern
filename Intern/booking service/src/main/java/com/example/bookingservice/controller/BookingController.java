package com.example.bookingservice.Controller;

import com.example.bookingservice.Api.BookingApi;
import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.Links;
import com.example.bookingservice.Model.Offers;
import com.example.bookingservice.Model.UserDetails;
import com.example.bookingservice.Service.bookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController implements BookingApi {
    private final bookingService bookingService;

    public BookingController(bookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public ResponseEntity<String> saveBus(BusDto busDto) {
        bookingService.saveBusService(busDto);
        return new ResponseEntity<>("Bus details successfully saved", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusDetails>> getAllBus(TravelDto travelDto) {
        return new ResponseEntity<>(bookingService.getAllBusService(travelDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LayoutDto> getBusLayout(GetLayoutDto getLayoutDto) {
        return new ResponseEntity<>(bookingService.getBusLayoutService(getLayoutDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> blockSeat(BlockDto blockDto) {
        return new ResponseEntity<>(bookingService.blockSeatService(blockDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveUser(UserDetails userDetails) {
        return new ResponseEntity<>(bookingService.saveUserService(userDetails), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RewardDto> makePayment(PaymentDto payment) {
        return new ResponseEntity<>(bookingService.makePayment(payment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelBooking(CancelDto cancelDto) {
        return new ResponseEntity<>(bookingService.cancelBookingSerice(cancelDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Offers>> saveRewardInformation(Offers offers) {
        return new ResponseEntity<>(bookingService.saveAllRewards(offers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Links>> saveLinks(Links links) {
        return new ResponseEntity<>(bookingService.saveLinksService(links), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OfferDto> getReward(Long id) {
        return new ResponseEntity<>(bookingService.getRewardService(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OfferDto>> getUserReward(String userId) {
        return new ResponseEntity<>(bookingService.getUserRewardService(userId), HttpStatus.OK);
    }
}
