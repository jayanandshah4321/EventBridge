package com.example.FindMates.service;

import com.example.FindMates.dto.EventCreateRequest;
import com.example.FindMates.dto.EventResponse;
import com.example.FindMates.dto.UserResponse;
import com.example.FindMates.model.Event;
import com.example.FindMates.model.User;
import com.example.FindMates.repository.EventRepository;
import com.example.FindMates.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public EventResponse createEvent(EventCreateRequest request) {
        // Validate request
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUserById(userId);
        if(user == null) {
            return null;
        }
        Event event = new Event(null, request.getTitle(), request.getLocation(), request.getDateTime(),
                request.getMaxPlayers(), 0, userId);
        eventRepository.save(event);
        return toResponse(event);
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event findByIdForUpdate(Long id) {
        return eventRepository.findByIdForUpdate(id).orElse(null);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public EventResponse toResponse(Event e) {
        return new EventResponse(e.getId(), e.getTitle(), e.getLocation(),
                e.getDateTime(), e.getMaxPlayers(), e.getBookedPlayers(), e.getCreatedByUserId());
    }
}
