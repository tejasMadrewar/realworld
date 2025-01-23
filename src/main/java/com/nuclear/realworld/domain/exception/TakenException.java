package com.nuclear.realworld.domain.exception;

public class TakenException extends BusinessException {
    public TakenException() {
        super("has already been taken");
    }
}
