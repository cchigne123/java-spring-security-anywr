package com.anywr.springsecuritytest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponseDto {
    private String token;
}
