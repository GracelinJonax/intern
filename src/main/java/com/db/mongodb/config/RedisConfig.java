package com.db.mongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {
    @Value("localhost")
    private String host;
    @Value("6379")
    private String port;

    @Bean
    public LettuceConnectionFactory redisConnection(){
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
        return new LettuceConnectionFactory(configuration);
    }
}
