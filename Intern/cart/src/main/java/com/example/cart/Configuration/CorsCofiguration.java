package com.example.cart.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsCofiguration implements WebMvcConfigurer {
    @Value("*")
    private String orginDomin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowCredentials(true)
              .allowedOrigins("*").allowedOriginPatterns("*");
    }
}

