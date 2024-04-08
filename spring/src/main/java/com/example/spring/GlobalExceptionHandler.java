package com.example.spring;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.spring.responses.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }
}