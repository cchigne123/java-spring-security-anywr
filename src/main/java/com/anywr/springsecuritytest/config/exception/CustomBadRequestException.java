package com.anywr.springsecuritytest.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"cause", "stackTrace", "suppressed", "localizedMessage"})
public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String systemMessage) {
        super(systemMessage);
    }
}