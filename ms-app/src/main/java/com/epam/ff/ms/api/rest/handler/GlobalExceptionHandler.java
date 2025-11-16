package com.epam.ff.ms.api.rest.handler;

import com.epam.ff.ms.core.exception.ModelNotFoundException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handle(
        final HttpMessageNotReadableException e,
        final HttpServletRequest request
    ) {
        final var cause = e.getCause();
        var message = "Invalid JSON input.";
        if (cause instanceof UnrecognizedPropertyException ex) {
            message = "Unrecognized field: [" + ex.getPropertyName() + "]";
        }
        return response(BAD_REQUEST, message, request);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(
        final ModelNotFoundException e,
        final HttpServletRequest request
    ) {
        return response(NOT_FOUND, e.getMessage(), request);
    }

    private static ResponseEntity<Map<String, Object>> response(
        final HttpStatus status,
        final String message,
        final HttpServletRequest request
    ) {
        final var body = new LinkedHashMap<String, Object>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", status.value());
        body.put("error", message);
        body.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
}
