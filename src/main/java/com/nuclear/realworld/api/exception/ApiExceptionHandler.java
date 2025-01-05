package com.nuclear.realworld.api.exception;

import com.nuclear.realworld.domain.exception.EmailNotAvailableException;
import com.nuclear.realworld.domain.exception.UsernameNotAvilableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UsernameNotAvilableException.class)
    public String handleUsernameAlreadyExistsException(UsernameNotAvilableException ex) {
        return "Error: Username already exists.";
    }

    @ExceptionHandler(EmailNotAvailableException.class)
    public String handleEmailAlreadyExistsException(EmailNotAvailableException ex) {
        return "Error: email already exists.";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMsg handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ErrorMsg(errors);
    }

    public class ErrorMsg {
        final public Map<String, String> errors;
        ErrorMsg(Map<String, String> errors) {
            this.errors = errors;
        }
    }
}
