package com.example.backendtest.config;

import com.example.backendtest.config.errormodels.ApiErrorMessage;
import com.example.backendtest.config.errormodels.InvalidFieldsMessage;
import com.example.backendtest.errors.BadRequest;
import com.example.backendtest.errors.EntityNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({EntityNotFound.class})
    public ResponseEntity<ApiErrorMessage>
        entityNotFound(EntityNotFound e) {
        ApiErrorMessage message = new ApiErrorMessage(e.getMessage());

        return ResponseEntity.status(422).body(message);
    }

    @ExceptionHandler({BadRequest.class})
    public ResponseEntity<ApiErrorMessage> badRequest(BadRequest e) {
        ApiErrorMessage message = new ApiErrorMessage(e.getMessage());

        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<InvalidFieldsMessage[]> argumentNotValid(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors= e.getBindingResult().getFieldErrors();

        InvalidFieldsMessage[] messages =fieldErrors.stream()
                .map(InvalidFieldsMessage::new).toArray(InvalidFieldsMessage[]::new);

        return ResponseEntity.status(400).body(messages);
    }
}
