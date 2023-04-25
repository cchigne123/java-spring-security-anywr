package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.config.exception.CustomBadRequestException;
import com.anywr.springsecuritytest.domain.User;
import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDto user) throws CustomBadRequestException {
        if (this.userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new CustomBadRequestException("Username " + user.getUsername()
                    + " is already taken. Choose another one");
        }
        User userDom = User.builder()
                .username(user.getUsername())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .build();
        this.userRepository.save(userDom);
    }

    @Override
    public UserDto findUser(String username) {
        Optional<User> optUser = this.userRepository.findUserByUsername(username);
        return optUser.map(user -> {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setLastname(user.getLastname());
            return userDto;
        }).orElse(null);
    }
}
