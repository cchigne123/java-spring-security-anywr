package com.anywr.springsecuritytest.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    public CustomExceptionHandler() {
        // Do nothing because this is a base class.
    }

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomBadRequestException badRequestException(CustomBadRequestException exception) {
        return exception;
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> invalidCredentialsException(AuthenticationException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errors", List.of(ex.getMessage()));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(x -> errors.add(x.getDefaultMessage()));
        if (errors.isEmpty()) errors.add("There's an error on the sent parameters");
        errorMap.put("errors", errors);
        return errorMap;
    }

}

