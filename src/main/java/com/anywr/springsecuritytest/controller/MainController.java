package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.dto.SignInRequestDto;
import com.anywr.springsecuritytest.dto.SignInResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MainController {

    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto request) {
        return new SignInResponseDto();
    }

}
