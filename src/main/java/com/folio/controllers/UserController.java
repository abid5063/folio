package com.folio.controllers;

import com.folio.models.User;
import com.folio.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById (@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }
}
