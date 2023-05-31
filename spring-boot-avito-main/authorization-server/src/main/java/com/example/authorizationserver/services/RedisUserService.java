package com.example.authorizationserver.services;

import com.example.authorizationserver.details.User;
import com.example.authorizationserver.details.UserDetailsImpl;
import com.example.authorizationserver.models.RedisUser;
import com.example.authorizationserver.repositories.RedisUserRepository;
import com.example.authorizationserver.repositories.UserRepository;
import com.example.customstarter.services.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisUserService {

    private final RedisUserRepository redisUserRepository;
    private final UserRepository userRepository;
    private final BlackListService blackListService;

    public void addTokenToUser(UserDetailsImpl userDetails, String accessToken) {
        User user = userDetails.getUser();
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUserRepository.findById(redisId)
                    .orElseThrow(() -> new UsernameNotFoundException("There's no redis user with id = " + redisId));

            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new HashSet<>());
            }

            redisUser.getTokens().add(accessToken);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Set.of(accessToken))
                    .build();
        }

        RedisUser savedRedisUser = redisUserRepository.save(redisUser);
        user.setRedisId(savedRedisUser.getId());

        userRepository.save(user);
    }

    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUserRepository.findById(user.getRedisId())
                    .orElseThrow(() -> new UsernameNotFoundException("Redis user not found " + user.getRedisId()));

            redisUser.getTokens().forEach(blackListService::add);
            redisUser.getTokens().clear();
            redisUserRepository.save(redisUser);
        }
    }
}
