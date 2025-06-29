package com.supemir.expense_manager.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.JMException;
import java.security.SignatureException;
import java.util.Map;

@ControllerAdvice()
public class JwtExceptionsHandler {
    @ExceptionHandler({MalformedJwtException.class, SignatureException.class, UnsupportedJwtException.class})
    public ResponseEntity<Map<String, Object>> handleExpiredJwtException(ExpiredJwtException ex) {
        return ExceptionResponseMaker.generateResponse("JWT token has expired", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JMException.class)
    public ResponseEntity<Map<String, Object>> handleJwtException(JMException ex) {
        return ExceptionResponseMaker.generateResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return ExceptionResponseMaker.generateResponse(ex.getMessage());
    }
}
