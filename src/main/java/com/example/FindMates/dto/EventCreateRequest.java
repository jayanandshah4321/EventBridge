package com.example.FindMates.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EventCreateRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "location is required")
    private String location;
    @JsonFormat(pattern = "dd-MM-yy HH:mm")
    @FutureOrPresent(message = "Event date must be today or in the future")
    private LocalDateTime dateTime;
    @Min(value = 1, message = "Max players must be at least 1")
    private int maxPlayers;
}
