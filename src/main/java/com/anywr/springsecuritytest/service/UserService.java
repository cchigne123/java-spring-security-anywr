package com.anywr.springsecuritytest.service;

import com.anywr.springsecuritytest.config.exception.CustomBadRequestException;
import com.anywr.springsecuritytest.dto.UserDto;

public interface UserService {
    void saveUser(UserDto user) throws CustomBadRequestException;
    UserDto findUser(String username);
}
