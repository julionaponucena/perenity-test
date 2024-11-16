package com.example.backendtest.config.errormodels;

import org.springframework.validation.FieldError;

public record InvalidFieldsMessage(String field, String message) {
    public InvalidFieldsMessage(FieldError objectError){
        this(
                objectError.getField(),
                objectError.getDefaultMessage()
        );
    }

}