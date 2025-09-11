package com.sist.ex0911_secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityJavaConfig {
    

    @Bean
    public PasswordEncoder aPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
