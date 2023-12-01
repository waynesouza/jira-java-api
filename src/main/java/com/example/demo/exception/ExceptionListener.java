package com.example.demo.exception;

import com.example.demo.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionListener {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorDTO> handleException(ResponseStatusException exception) {
        return buildResponse(HttpStatus.valueOf(exception.getStatusCode().value()),
                Objects.requireNonNullElse(exception.getReason(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleGenericExceptions(Exception exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<ApiErrorDTO> buildResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(new ApiErrorDTO(status.value(), status.getReasonPhrase(), message), status);
    }

}
