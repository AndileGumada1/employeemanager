package com.andile.gumada.employee.manager.exception;

public class MissingConfigValueException extends RuntimeException {
    public MissingConfigValueException(String message) {
        super(message);
    }
}
