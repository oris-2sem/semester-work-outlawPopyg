package com.example.authorizationserver.repositories;

import com.example.authorizationserver.models.RedisUser;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}
