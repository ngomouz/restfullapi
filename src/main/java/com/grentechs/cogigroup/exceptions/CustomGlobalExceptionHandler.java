package com.grentechs.cogigroup.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
                ex.getMessage().toUpperCase(), request.getDescription(false) );
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage().toUpperCase(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    protected final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), exception.getMessage().toUpperCase(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), exception.getMessage().toUpperCase(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), exception.getMessage().toUpperCase(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    protected final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), exception.getMessage().toUpperCase(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }
}
