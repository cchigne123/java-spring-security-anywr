package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.dto.UserDto;

public interface UserService {
    String saveUser(UserDto user);
    UserDto findUser(String username);
}
