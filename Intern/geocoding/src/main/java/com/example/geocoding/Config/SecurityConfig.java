package com.example.geocoding.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("user")
                .password("hai")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(requests->requests
                        .requestMatchers("/login").authenticated()
                );
        return http.build();
    }
}
