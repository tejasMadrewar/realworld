package com.nuclear.realworld.domain.exception;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException() {
        super("Not found");
    }
}
