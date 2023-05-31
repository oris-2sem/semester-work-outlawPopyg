package com.example.customstarter.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class BlackListRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        return hasToken != null && hasToken;
    }
}
