package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.domain.User;
import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDto user) {
        User userDom = User.builder()
                .username(user.getUsername())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .build();
        this.userRepository.save(userDom);
    }
}
