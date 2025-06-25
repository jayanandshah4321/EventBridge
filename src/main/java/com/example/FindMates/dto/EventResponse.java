package com.example.FindMates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String location;
    private LocalDateTime dateTime;
    private int maxPlayers;
    private int bookedPlayers;
    private Long createdByUserId;
}
