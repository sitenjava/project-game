package com.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
//@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField()  + ": " + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName()  + ": " + error.getDefaultMessage());
        }

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }*/

    /*@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleUnauthorizedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }*/

}
