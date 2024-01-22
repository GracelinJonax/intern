package com.example.bookingservice.Dto;

import com.example.bookingservice.Model.Reward;
import lombok.Data;

@Data
public class RewardDto {
//    private String rewardId;
//    private String couponCode;
    private TicketDto ticket;
    private Reward reward;
}
