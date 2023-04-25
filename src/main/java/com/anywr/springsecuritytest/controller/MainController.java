package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.config.exception.CustomBadRequestException;
import com.anywr.springsecuritytest.dto.SignInRequestDto;
import com.anywr.springsecuritytest.dto.SignInResponseDto;
import com.anywr.springsecuritytest.dto.UserDto;
import com.anywr.springsecuritytest.service.CustomUserDetailService;
import com.anywr.springsecuritytest.service.JwtService;
import com.anywr.springsecuritytest.service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/signUp")
    public void saveUser(@Valid @RequestBody UserDto user) throws CustomBadRequestException {
        this.userService.saveUser(user);
    }

    @PostMapping("/signIn")
    @ResponseBody
    public SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto request) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        String token = jwtService.createToken(userDetails);
        return SignInResponseDto.builder().token(token).expiresAt(jwtService.getTokenExpiration(token)).build();
    }

}
