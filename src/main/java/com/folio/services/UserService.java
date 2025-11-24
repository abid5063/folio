package com.folio.services;

import com.folio.dtos.LoginRequest;
import com.folio.dtos.LoginResponse;
import com.folio.dtos.RegisterRequest;
import com.folio.models.User;
import com.folio.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new LoginResponse(false, "Email already registered.");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            return new LoginResponse(false, "Username already taken.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setDesignation(request.getDesignation());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return new LoginResponse(true, "User registered successfully.");
    }

    public LoginResponse login(LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .map(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        return new LoginResponse(true, "Login successful.");
                    } else {
                        return new LoginResponse(false, "Invalid password.");
                    }
                })
                .orElse(new LoginResponse(false, "User not found."));
    }

}
