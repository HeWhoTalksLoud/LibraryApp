package com.skypro.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleException(
            IllegalArgumentException exception) {
        ExceptionMessage message = new ExceptionMessage();
        message.setMessage(exception.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

