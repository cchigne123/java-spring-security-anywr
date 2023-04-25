package com.anywr.springsecuritytest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SignInResponseDto {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private Date expiresAt;
}
