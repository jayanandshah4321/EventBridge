package com.example.FindMates.repository;

import com.example.FindMates.model.Booking;
import com.example.FindMates.model.Event;
import com.example.FindMates.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByEventId(Long eventId);
}

