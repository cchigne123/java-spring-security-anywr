package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }
}
