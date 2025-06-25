package com.example.FindMates.controller;

import com.example.FindMates.dto.BookingResponse;
import com.example.FindMates.dto.UserResponse;
import com.example.FindMates.model.Event;
import com.example.FindMates.model.User;
import com.example.FindMates.service.BookingService;
import com.example.FindMates.service.EventService;
import com.example.FindMates.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookEvent(@RequestParam Long eventId,@RequestParam int numberOfPlayers) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingResponse response = bookingService.bookEvent(userId, eventId,numberOfPlayers);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public List<BookingResponse> myBookings() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getUserBookings(userId);
    }

    @GetMapping("/")
    @SecurityRequirement(name = "bearerAuth")
    public List<BookingResponse> getAllBookingsByEventId(@RequestParam Long eventId) {
        return bookingService.getAllBookingsByEventId(eventId);
    }
}

