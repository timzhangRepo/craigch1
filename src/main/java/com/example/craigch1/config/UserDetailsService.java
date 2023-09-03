package com.example.craigch1.config;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsService loadUserByUsername(String username) throws
            UsernameNotFoundException;
}
