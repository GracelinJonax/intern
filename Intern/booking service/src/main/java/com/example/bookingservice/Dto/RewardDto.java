package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.Rewards;
import lombok.Data;

@Data
public class RewardDto {
//    private String rewardId;
//    private String couponCode;
    private TicketDto ticket;
    private Rewards rewards;
}
