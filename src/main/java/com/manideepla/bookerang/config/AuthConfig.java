package com.manideepla.bookerang.config;

import com.manideepla.bookerang.handlers.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {

    @Autowired
    UserHandler userHandler;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager manager = new UserDetailsRepositoryReactiveAuthenticationManager(userHandler);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }
}
