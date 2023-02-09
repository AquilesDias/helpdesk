package com.aquiles.helpdesk.resources.exceptions;

import com.aquiles.helpdesk.service.exception.DataIntegrityViolationException;
import com.aquiles.helpdesk.service.exception.ObjectNotFoundException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request){

        StandardError error = new StandardError(

                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Object not found!",
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> objectNotFoundException(DataIntegrityViolationException exception, HttpServletRequest request){

        StandardError error = new StandardError(

                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de dados!",
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException exception, HttpServletRequest request){

        ValidationError errors = new ValidationError(

                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação!",
                "Erro na validação dos campos",
                request.getRequestURI());

        for(FieldError x : exception.getBindingResult().getFieldErrors()){
            errors.addErrors(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
