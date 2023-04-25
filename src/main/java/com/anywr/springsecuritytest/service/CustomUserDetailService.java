package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.domain.User;
import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = this.userRepository.findUserByUsername(username);
        if (optUser.isEmpty()) {
            logger.error("User {} does not exist", username);
            throw new UsernameNotFoundException("User " + username + " does not exist");
        }
        User user = optUser.get();
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
