package com.supemir.expense_manager.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@Data
public class ExceptionResponseMaker {
    private static final String message = "An error occurred";

    // main response method
    public static ResponseEntity<Map<String, Object>> generateResponse(String message, Map<String, String> errors, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("errors", errors);

        return new ResponseEntity<>(response, status);
    }

    // Default response with OK status and empty errors
    public static ResponseEntity<Map<String, Object>> generateResponse(Map<String, String> errors, HttpStatus status) {
        return generateResponse(message, errors, status);
    }


    // Dynamic response with empty errors
    public static ResponseEntity<Map<String, Object>> generateResponse(String message, HttpStatus status ) {
        return generateResponse(message, null, status);
    }

    // Default response with BAD_REQUEST status and empty errors
    public static ResponseEntity<Map<String, Object>> generateResponse(String message, Map<String, String> errors) {
        return generateResponse(message, errors, HttpStatus.BAD_REQUEST);
    }

    // Default response with OK status and empty errors
    public static ResponseEntity<Map<String, Object>> generateResponse(String message) {
        return generateResponse(message, null, HttpStatus.BAD_REQUEST);
    }

    // Default response with BAD_REQUEST status
    public static ResponseEntity<Map<String, Object>> generateResponse() {
        return generateResponse(message, null, HttpStatus.BAD_REQUEST);
    }
}
