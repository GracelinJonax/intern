package com.example.bookingservice.Service;

import com.example.bookingservice.Dto.*;
import com.example.bookingservice.Model.BusDetails;
import com.example.bookingservice.Model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface bookingService {

    void saveBusService(BusDto busDto);

    List<BusDetails> getAllBusService(TravelDto travelDto);

    LayoutDto getBusLayoutService(getLayoutDto getLayoutDto);

    String blockSeatService(BlockDto blockDto);

    String saveUserService(UserDetails userDetails);

    TicketDto makePayment(PaymentDto payment);

    String cancelBookingSerice(CancelDto cancelDto);
}
