package com.example.customstarter.services;

import com.example.customstarter.repositories.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class BlackListService {
    private final BlackListRepository repository;

    public void add(String token) {
        repository.save(token);
    }

    public boolean exists(String token) {
        return repository.exists(token);
    }
}
