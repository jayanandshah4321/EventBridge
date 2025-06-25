package com.example.FindMates.controller;

import com.example.FindMates.dto.EventCreateRequest;
import com.example.FindMates.dto.EventResponse;
import com.example.FindMates.model.User;
import com.example.FindMates.service.EventService;
import com.example.FindMates.service.UserService;
import com.sun.jdi.request.EventRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreateRequest request) {
        EventResponse eventResponse = eventService.createEvent(request);
        return eventResponse != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(eventResponse) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<EventResponse> listEvents() {
        return eventService.getAllEvents();
    }
}