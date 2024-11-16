package com.example.backendtest.errors;

public abstract class EntityNotFound extends RuntimeException {
    protected EntityNotFound(String message) {
        super(message);
    }
}
