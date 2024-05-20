package com.example.bookingservice.dto;

import com.example.bookingservice.model.Reward;
import lombok.Data;

@Data
public class RewardDto {
    private TicketDto ticket;
    private Reward reward;
}
