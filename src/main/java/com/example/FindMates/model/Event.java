package com.example.FindMates.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private LocalDateTime dateTime;
    private int maxPlayers;
    private int bookedPlayers;
    private Long createdByUserId;
}
