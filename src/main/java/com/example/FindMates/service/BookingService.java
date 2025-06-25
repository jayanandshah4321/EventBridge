package com.example.FindMates.service;

import com.example.FindMates.dto.BookingResponse;
import com.example.FindMates.dto.UserResponse;
import com.example.FindMates.model.Booking;
import com.example.FindMates.model.Event;
import com.example.FindMates.model.User;
import com.example.FindMates.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Transactional
    public BookingResponse bookEvent(Long userId, Long eventId,int numberOfPlayers) {
        Event event = eventService.findByIdForUpdate(eventId);
        User user = userService.getUserById(userId);
        if (user==null || event == null || event.getBookedPlayers()+numberOfPlayers > event.getMaxPlayers()) return null;

        Booking booking = new Booking(null, userId, eventId, LocalDateTime.now(), numberOfPlayers);
        bookingRepository.save(booking);

        event.setBookedPlayers(event.getBookedPlayers() + numberOfPlayers);
        eventService.save(event);

        return toResponse(booking);
    }

    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse toResponse(Booking booking) {
        User user = userService.getUserById(booking.getUserId());
        Event event = eventService.getEventById(booking.getEventId());
        return new BookingResponse(
                booking.getId(),
                userService.toResponse(user),
                eventService.toResponse(event),
                booking.getBookingTime(),
                booking.getNumberOfPlayers()
        );
    }

    public List<BookingResponse> getAllBookingsByEventId(Long eventId) {
        return bookingRepository.findByEventId(eventId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}