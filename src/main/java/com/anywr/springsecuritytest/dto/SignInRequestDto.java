package com.anywr.springsecuritytest.dto;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String username;
    private String password;
}
