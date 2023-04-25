package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public UserDto getUser(Authentication authentication) {
        return this.userService.findUser(authentication.getName());
    }

}
