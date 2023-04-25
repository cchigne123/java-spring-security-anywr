package com.anywr.springsecuritytest.controller;

import com.anywr.springsecuritytest.dto.SignInRequestDto;
import com.anywr.springsecuritytest.dto.SignInResponseDto;
import com.anywr.springsecuritytest.service.CustomUserDetailService;
import com.anywr.springsecuritytest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    @PostMapping("/signIn")
    @ResponseBody
    public SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto request) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        String token = jwtService.createToken(userDetails);
        return SignInResponseDto.builder().token(token).build();
    }

}
