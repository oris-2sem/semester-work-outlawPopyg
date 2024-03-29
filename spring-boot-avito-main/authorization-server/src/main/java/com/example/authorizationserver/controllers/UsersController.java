package com.example.authorizationserver.controllers;

import com.example.authorizationserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping("/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable UUID userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }
}
