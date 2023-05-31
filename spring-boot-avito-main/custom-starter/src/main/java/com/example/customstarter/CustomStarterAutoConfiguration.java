package com.example.customstarter;

import com.example.customstarter.filters.BlackListFilter;
import com.example.customstarter.repositories.BlackListRepository;
import com.example.customstarter.services.BlackListService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CustomStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BlackListRepository blackListRepository(RedisTemplate<String, String> redisTemplate) {
        return new BlackListRepository(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public BlackListService blackListService(BlackListRepository blackListRepository) {
        return new BlackListService(blackListRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public BlackListFilter blackListFilter(BlackListService blackListService) {
        return new BlackListFilter(blackListService);
    }
}
