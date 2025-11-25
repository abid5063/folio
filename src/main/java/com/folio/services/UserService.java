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

    public User getUserByUserId(Long userid) {
        return userRepository.findByUserid(userid);
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new LoginResponse(false, "Email already registered.", null, null);
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            return new LoginResponse(false, "Username already taken.", null, null);
        }

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setDesignation(request.getDesignation());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return new LoginResponse(true, "User registered successfully.",
                user.getUsername(), user.getUserid());
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) return new LoginResponse(false, "User not found", null, null);
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(true, "Login successful.",
                    user.getUsername(), user.getUserid());
        } else {
            return new LoginResponse(false, "Invalid password.",
                    null, null);
        }
    }

}
