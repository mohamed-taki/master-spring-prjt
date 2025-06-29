package com.supemir.expense_manager.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@ControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, String> errors = new HashMap<>();
    private String message = "An error occurred";

    // Handle all invalid form validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        this.setErrors(
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                error -> error.getField(),
                                error -> error.getDefaultMessage(),
                                (existing, replacement) -> existing // in case of duplicate keys
                        ))
        );

        this.setMessage("Validation failed");
        this.setErrors(errors);

        return generateResponse();
    }

    // Handle bad JSON parsing
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex) {
        this.setMessage(ex.getMessage());
        return generateResponse();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlreadyExists(IllegalArgumentException ex) {
        this.setMessage(ex.getMessage());
        return generateResponse(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        this.setMessage(ex.getMessage());
        return generateResponse(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(GeneralException ex) {
        this.setMessage(ex.getMessage());
        return generateResponse();
    }

    // main response method
    private ResponseEntity<Map<String, Object>> generateResponse(HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", this.message);
        response.put("errors", this.errors);

        return new ResponseEntity<>(response, status);
    }
    // Default response with BAD_REQUEST status
    private ResponseEntity<Map<String, Object>> generateResponse() {
        return generateResponse(HttpStatus.BAD_REQUEST);
    }
}
