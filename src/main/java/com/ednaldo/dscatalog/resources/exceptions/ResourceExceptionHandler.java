package com.ednaldo.dscatalog.resources.exceptions;

import com.ednaldo.dscatalog.services.exceptions.DataBaseIntegrityException;
import com.ednaldo.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(ResourceNotFoundException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource Not Found.");
        error.setMessage(e.getMessage());
        error.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseIntegrityException.class)
    public ResponseEntity<StandardError> dataBaseIntegrityException(DataBaseIntegrityException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource Not Found.");
        error.setMessage(e.getMessage());
        error.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
