package com.nuclear.realworld.api.exception;

import com.nuclear.realworld.domain.exception.BusinessException;
import com.nuclear.realworld.domain.exception.EmailNotAvailableException;
import com.nuclear.realworld.domain.exception.ProfileNotFoundException;
import com.nuclear.realworld.domain.exception.UsernameNotAvilableException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UsernameNotAvilableException.class)
    public String handleUsernameAlreadyExistsException(
            UsernameNotAvilableException ex) {
        return "Error: Username already exists.";
    }

    @ExceptionHandler(EmailNotAvailableException.class)
    public String handleEmailAlreadyExistsException(
            EmailNotAvailableException ex) {
        return "Error: email already exists.";
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public String handleProfileNotFoundException(ProfileNotFoundException ex) {
        return "Error: Profile not found.";
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBusinessxception(BusinessException ex,
                                         WebRequest request) {
        return "Error: " + ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMsg handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
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
