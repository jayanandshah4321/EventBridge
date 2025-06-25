package com.example.FindMates.service;

import com.example.FindMates.dto.UserLoginRequest;
import com.example.FindMates.dto.UserRegisterRequest;
import com.example.FindMates.dto.UserResponse;
import com.example.FindMates.model.User;
import com.example.FindMates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return null; // Email already exists
        }
        User user = new User(null, request.getName(), request.getEmail(), request.getPassword());
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public UserResponse login(UserLoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail()))
                .orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}
