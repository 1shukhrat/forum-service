package ru.saynurdinov.demo.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.saynurdinov.demo.forum.DTO.ErrorMessage;
import ru.saynurdinov.demo.forum.exception.UserIsAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), 403), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessage> handleBindException(BindException e) {
        BindingResult br = e.getBindingResult();
        String[] errors = br.getAllErrors().stream().map(ObjectError::getDefaultMessage).toArray(String[]::new);
        String errorMessage = "Validation Error: " + Arrays.toString(errors);
        return new ResponseEntity<>(new ErrorMessage(errorMessage, LocalDateTime.now(), 422), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserIsAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserIsAlreadyExistsException(UserIsAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), 409), HttpStatus.CONFLICT);
    }
}
