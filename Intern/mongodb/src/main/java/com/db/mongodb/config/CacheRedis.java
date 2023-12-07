package com.db.mongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
public class CacheRedis {
    @Bean
    public RedisCacheManager RedisManager(RedisConnectionFactory redisCon){
        RedisCacheConfiguration redcon=customConfig(Duration.ofMinutes(1)).disableCachingNullValues();
//        return RedisCacheManager.create(redisCon);
        return RedisCacheManager.builder(redisCon).cacheDefaults(redcon)
                .withCacheConfiguration("first",customConfig(Duration.ofMinutes(1)))
                .build();
    }

    private RedisCacheConfiguration customConfig(Duration duration){
    return  RedisCacheConfiguration.defaultCacheConfig().entryTtl(duration);
    }
}
