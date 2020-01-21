package com.grentechs.cogigroup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public CustomErrorDetails usernameNotFound(UsernameNotFoundException exception, WebRequest request) {
        return new CustomErrorDetails(LocalDateTime.now(), exception.getMessage().toUpperCase(), request.getDescription(false));
    }
}
