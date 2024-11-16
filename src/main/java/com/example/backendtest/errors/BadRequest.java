package com.example.backendtest.errors;

public abstract class BadRequest extends RuntimeException {
    protected BadRequest(String message) {
        super(message);
    }
}
