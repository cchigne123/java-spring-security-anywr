package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.domain.User;
import com.anywr.springsecuritytest.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(User user){
        this.userService.saveUser(user);
    }

}
