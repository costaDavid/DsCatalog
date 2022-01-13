package com.Costa.DsCatalog.controller.exceptions;

import com.Costa.DsCatalog.service.exceptions.DataBaseException;
import com.Costa.DsCatalog.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
            StandardError erro = new StandardError();
            erro.setTimestamp(Instant.now());
            erro.setStatus(HttpStatus.NOT_FOUND.value());
            erro.setError("Resource not found");
            erro.setMessage(e.getMessage());
            erro.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }

        @ExceptionHandler(DataBaseException.class)
        public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
            StandardError erro = new StandardError();
            erro.setTimestamp(Instant.now());
            erro.setStatus(HttpStatus.BAD_REQUEST.value());
            erro.setError("Database exception");
            erro.setMessage(e.getMessage());
            erro.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
}

