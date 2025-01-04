package com.nuclear.realworld.api.exception;

import com.nuclear.realworld.domain.exception.EmailNotAvilableException;
import com.nuclear.realworld.domain.exception.UsernameNotAvilableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UsernameNotAvilableException.class)
    public String handleUsernameAlreadyExistsException(UsernameNotAvilableException ex) {
        return "Error: Username already exists.";
    }

    @ExceptionHandler(EmailNotAvilableException.class)
    public String handleEmailAlreadyExistsException(EmailNotAvilableException ex) {
        return "Error: email already exists.";
    }
}
