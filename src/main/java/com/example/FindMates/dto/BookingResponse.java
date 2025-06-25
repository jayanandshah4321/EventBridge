package com.example.FindMates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private UserResponse user;
    private EventResponse event;
    private LocalDateTime bookingTime;
    private int numberOfPlayers;
}
