package com.nuclear.realworld.api.exception;

import com.nuclear.realworld.api.security.exception.EmailNotFoundException;
import com.nuclear.realworld.domain.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC_ERROR_MESSAGE = "Oops! Something went wrong.";

    private Error.Builder createErrorBuilder(String message) {
        return Error.Builder.builder().status("error").message(message);
    }

    private Error.Builder createErrorBuilder(Map<String, Object> errors) {
        return Error.Builder.builder().errors(errors);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode status,
                                                             WebRequest request) {

        if (body == null || body instanceof String) {
            body = createErrorBuilder(GENERIC_ERROR_MESSAGE).build();
        }
        return super.handleExceptionInternal(ex,
                                             body,
                                             headers,
                                             status,
                                             request);
    }

    private ResponseEntity<?> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request, String name) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = createErrorBuilder(toMap(name, ex.getMessage())).build();

        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<?> handleArticleNotFound(ArticleNotFoundException ex,
                                                   WebRequest request) {
        return handleResourceNotFound(ex, request, "article");
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> handleEmailNotFound(EmailNotFoundException ex,
                                                 WebRequest request) {
        return handleResourceNotFound(ex, request, "email");
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<?> handleTagNotFound(TagNotFoundException ex,
                                               WebRequest request) {
        return handleResourceNotFound(ex, request, "email");
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<?> handleProfileNotFound(ProfileNotFoundException ex,
                                                   WebRequest request) {
        return handleResourceNotFound(ex, request, "profile");
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> handleCommentNotFound(CommentNotFoundException ex,
                                                   WebRequest request) {
        return handleResourceNotFound(ex, request, "comment");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex,
                                                     WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = createErrorBuilder(ex.getMessage()).build();
        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    private ResponseEntity<?> handleTaken(TakenException ex, WebRequest request,
                                          String fieldName) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = ex.getMessage();

        Error error = createErrorBuilder(toMap(fieldName, message)).build();
        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<?> handleEmailTaken(EmailTakenException ex,
                                              WebRequest request) {
        return handleTaken(ex, request, "email");
    }

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<?> handleUsernameTaken(UsernameTakenException ex,
                                                 WebRequest request) {
        return handleTaken(ex, request, "username");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex,
                                                WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String message = "You don't have access to this resource";
        Error error = createErrorBuilder(message).build();

        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    @ExceptionHandler(ArticleNotUniqueException.class)
    public ResponseEntity<?> handleArticleNotUnique(
            ArticleNotUniqueException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = ex.getMessage();
        Error error = createErrorBuilder(toMap("title", message)).build();

        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex,
                                                   WebRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = ex.getMessage();
        Error error = createErrorBuilder(message).build();
        return handleExceptionInternal(ex,
                                       error,
                                       new HttpHeaders(),
                                       status,
                                       request);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        return handleValidationInternal(ex,
                                        ex.getBindingResult(),
                                        headers,
                                        status,
                                        request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex,
                                                            BindingResult bindingResult,
                                                            HttpHeaders headers,
                                                            HttpStatusCode statusCode,
                                                            WebRequest request) {
        var status = HttpStatus.valueOf(statusCode.value());

        var map = new HashMap<String, Object>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(),
                    toList(fieldError.getDefaultMessage()));
        });

        var error = createErrorBuilder(map).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private List<String> toList(String message) {
        ArrayList<String> list = new ArrayList<>();
        list.add(message);
        return list;
    }

    private Map<String, Object> toMap(String field, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(field, toList(message));
        return map;
    }

}
