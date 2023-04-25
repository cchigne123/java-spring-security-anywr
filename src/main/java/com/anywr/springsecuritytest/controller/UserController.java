package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
