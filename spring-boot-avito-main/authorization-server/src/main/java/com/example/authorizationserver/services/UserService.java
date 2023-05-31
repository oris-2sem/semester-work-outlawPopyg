package com.example.authorizationserver.services;

import com.example.authorizationserver.details.User;
import com.example.authorizationserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RedisUserService redisUserService;
    private final UserRepository userRepository;
    public void blockUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " not found"));

        redisUserService.addAllTokensToBlackList(user);
    }
}
